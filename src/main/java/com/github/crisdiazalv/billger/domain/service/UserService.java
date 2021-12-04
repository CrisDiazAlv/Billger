package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.User;

public interface UserService {

    void save(User user);

    User findById(long id);

    void addAccount(long id, Account account);

}
