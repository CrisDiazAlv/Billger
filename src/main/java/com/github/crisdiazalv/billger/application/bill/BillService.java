package com.github.crisdiazalv.billger.application.bill;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.repository.BillRepository;
import com.github.crisdiazalv.billger.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BillService {

    private final BillRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public BillService(BillRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Bill> findAll(long accountId) {
        User user = findUser();
        findAccountOrThrow(accountId, user.getAccounts());
        return repository.findAll(accountId);
    }

    @Transactional(readOnly = true)
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
    public Map<Category, List<Bill>> findGroupedByCategory(long account) {
        User user = findUser();
        List<Bill> bills = user.getAccounts()
                .stream()
                .filter(a -> a.getId() == account)
                .flatMap(a -> a.getBills().stream())
                .sorted(Comparator.comparing(Bill::getDate))
                .toList();
        return bills.stream()
                .collect(Collectors.groupingBy(Bill::getCategory));
    }

    @Transactional
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
    public void deleteById(long id) {
        Bill bill = findById(id);
        log.info("Deleting bill '{}'", bill.getDescription());
        Account account = bill.getAccount();
        account.setCurrentBalance(account.getCurrentBalance() - bill.getAmount());
        // first, remove it from the account
        account.getBills().remove(bill);
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
