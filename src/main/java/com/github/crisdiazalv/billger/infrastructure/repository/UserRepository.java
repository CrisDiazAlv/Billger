package com.github.crisdiazalv.billger.infrastructure.repository;

import com.github.crisdiazalv.billger.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
