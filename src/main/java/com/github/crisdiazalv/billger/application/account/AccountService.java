package com.github.crisdiazalv.billger.application.account;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.repository.AccountRepository;
import com.github.crisdiazalv.billger.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository repository;
    private final UserRepository userRepository;

    private Random random = new Random();

    @Autowired
    public AccountService(AccountRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        user.getAccounts()
                .forEach(a -> log.info("Found {} bills on '{}' account", a.getBills().size(), a.getName()));
        return user.getAccounts();
    }

    @Transactional(readOnly = true)
    public Account findById(long id) {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        Account account = user.getAccounts()
                .stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La cuenta no existe"));
        log.info("Found {} bills on '{}' account", account.getBills().size(), account.getName());
        return account;
    }

    @Transactional
    public void save(Account account) {
        User user = getUser();
        account.setUser(user);
        account.setCreationDate(LocalDateTime.now());
        List<String> colors = List.of("#2b6777", "#5f2c3e", /*"#f2f2f2",*/ "#52ab98", /*"#f5cac2",*/ "#6db785", "#ef9273", "#de5499");
        account.setColor(colors.get(random.nextInt(colors.size())));
        log.info("Saving new account: {}", account);
        repository.save(account);
    }

    @Transactional
    public void deleteById(long id) {
        Account account = findById(id);
        log.info("Deleting account '{}'", account.getName());
        // first, remove it from the user
        account.getUser().getAccounts().remove(account);
        // delete it
        repository.deleteById(id);
    }

    private User getUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}
