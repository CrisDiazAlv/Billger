package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Account;

import java.util.List;

public interface AccountService {

    void save(Account account);

    Account findById(long id);
}
