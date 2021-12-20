package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }

    @Override
    public Account findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("La cuenta no existe"));
    }

    @Override
    public List<Account> findAll() {
        return getUser().getAccounts();
    }

    private User getUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}
