package com.github.crisdiazalv.billger.infrastructure.repository.account;

import com.github.crisdiazalv.billger.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}
