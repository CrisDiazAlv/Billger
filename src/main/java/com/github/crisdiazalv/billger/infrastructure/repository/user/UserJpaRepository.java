package com.github.crisdiazalv.billger.infrastructure.repository.user;

import com.github.crisdiazalv.billger.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
