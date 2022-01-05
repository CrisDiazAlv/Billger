package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long id);

    void save(Account account);

}
