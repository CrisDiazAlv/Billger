package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAll() {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("No existe un usuario con ese username"));
        return user.getAccounts();
    }

    @Transactional(readOnly = true)
    @Override
    public Account findById(long id) {
        User user = userRepository.findByUsername(getUser().getUsername())
                .orElseThrow(() -> new NotFoundException("No existe un usuario con ese username"));
        return user.getAccounts()
                .stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("La cuenta no existe"));
    }

    @Transactional
    @Override
    public void save(Account account) {
        User user = getUser();
        account.setUser(user);
        account.setCreationDate(LocalDateTime.now());
        user.getAccounts().add(account);
        log.info("Saving account {}", account.getName());
        userRepository.save(user);
        //repository.save(account);
    }

    private User getUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}
