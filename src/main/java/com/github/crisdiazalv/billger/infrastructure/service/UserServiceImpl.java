package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.service.UserService;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public User findMe(String username) {
        log.info("Searching for user '{}'", username);
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
    }

}
