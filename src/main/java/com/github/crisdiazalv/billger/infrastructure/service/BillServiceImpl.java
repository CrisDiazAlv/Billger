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
public class BillServiceImpl implements BillService {

    private final BillRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public BillServiceImpl(BillRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Bill> findAll(Long accountId) {
        User user = findUser();
        Account account = getAccount(accountId, user);
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

    @Override
    public Map<LocalDate, List<Bill>> findAllGroupedByDate(Long category) {
        List<Bill> bills = repository.findAll();
        // coger todas las facturas y agruparlas por fecha
        return bills.stream().collect(Collectors.groupingBy(bill -> bill.getDate().toLocalDate()));
    }

    @Override
    public Map<Category, List<Bill>> findAllGroupedByCategory() {
        List<Bill> bills = repository.findAll();
        // coger todas las facturas y agruparlas por categoria
        return bills.stream().collect(Collectors.groupingBy(Bill::getCategory));
    }

    @Override
    public void save(Bill bill) {
        User user = findUser();
        // check if the account is property of the user
        Account account = getAccount(bill.getAccount().getId(), user);
        // update account balance
        account.setCurrentBalance(account.getCurrentBalance() + bill.getAmount());
        // check if the category is property of the user
        Category category = getCategory(bill, user);
        bill.setAccount(account);
        bill.setCategory(category);
        log.info("Saving new bill: {}", bill);
        repository.save(bill);
    }

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

    private Account getAccount(long id, User user) {
        return user.getAccounts()
                .stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La cuenta no existe"));
    }

    private Category getCategory(Bill bill, User user) {
        return user.getCategories()
                .stream()
                .filter(c -> c.getId() == bill.getCategory().getId())
                .findAny()
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));
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
