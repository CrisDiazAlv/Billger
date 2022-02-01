package com.github.crisdiazalv.billger.domain.repository;

import com.github.crisdiazalv.billger.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

}
