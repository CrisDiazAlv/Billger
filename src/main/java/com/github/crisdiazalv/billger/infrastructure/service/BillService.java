package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.infrastructure.repository.BillRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BillService implements BillService {

    private final BillRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public BillService(BillRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bill> findAll(long accountId) {
        User user = findUser();
        Account account = findAccountOrThrow(accountId, user.getAccounts());
        List<Specification<Bill>> specifications = new ArrayList<>();
        specifications.add(equals("account", account));
        Specification<Bill> specification = null;
        for (Specification<Bill> s : specifications) {
            if (specification == null) {
                specification = Specification.where(s);
            } else {
                specification.and(s);
            }
        }
        return repository.findAll(specification);
    }

    private Specification<Bill> equals(String field, Object id) {
        return (root, query, cb) -> cb.equal(root.get(field), id);
    }

    @Transactional(readOnly = true)
    @Override
    public Bill findById(long id) {
        User user = findUser();
        return user.getAccounts()
                .stream()
                .flatMap(a -> a.getBills().stream())
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("El recibo no existe"));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<LocalDate, List<Bill>> findAllGroupedByDate(Long category) {
        List<Bill> bills = repository.findAll();
        // coger todas las facturas y agruparlas por fecha
        return bills.stream().collect(Collectors.groupingBy(bill -> bill.getDate().toLocalDate()));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Category, List<Bill>> findAllGroupedByCategory() {
        List<Bill> bills = repository.findAll();
        // coger todas las facturas y agruparlas por categoria
        return bills.stream().collect(Collectors.groupingBy(Bill::getCategory));
    }

    @Transactional
    @Override
    public void save(Bill bill) {
        User user = findUser();
        // check if the account is property of the user
        Account account = findAccountOrThrow(bill.getAccount().getId(), user.getAccounts());
        // update account balance
        account.setCurrentBalance(account.getCurrentBalance() + bill.getAmount());
        // check if the category is property of the user
        Category category = findCategoryOrThrow(bill.getCategory().getId(), user.getCategories());
        bill.setAccount(account);
        bill.setCategory(category);
        log.info("Saving new bill: {}", bill);
        repository.save(bill);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Bill bill = findById(id);
        log.info("Deleting bill '{}'", bill.getDescription());
        // first, remove it from the account
        bill.getAccount().getBills().remove(bill);
        // delete it
        repository.deleteById(id);
    }

    private Account findAccountOrThrow(long id, List<Account> accounts) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La cuenta seleccionada no existe"));
    }

    private Category findCategoryOrThrow(long id, List<Category> categories) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La categoría seleccionada no existe"));
    }

    private User findUser() {
        return userRepository.findByUsername(getPrincipal().getUsername())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
    }

    private UserPrincipal getPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
