package com.github.crisdiazalv.billger.infrastructure.repository.account;

import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.repository.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository repository;

    public AccountRepositoryImpl(AccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
