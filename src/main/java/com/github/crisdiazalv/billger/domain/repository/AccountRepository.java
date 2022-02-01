package com.github.crisdiazalv.billger.domain.repository;

import com.github.crisdiazalv.billger.domain.model.Account;

public interface AccountRepository {

    Account save(Account account);

    void deleteById(Long id);

}
