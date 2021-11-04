package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.BillNotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }

    @Override
    public Account findById(long id) {
        Optional<Account> account = repository.findById(id);
        if (account.isEmpty()) {
            throw new BillNotFoundException("La cuenta no existe");
        }
        return account.get();
    }


}

