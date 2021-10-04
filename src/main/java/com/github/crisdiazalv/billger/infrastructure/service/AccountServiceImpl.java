package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public void save(Account account) {
        repository.save(account);
    }


}

