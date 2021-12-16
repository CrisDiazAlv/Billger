package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.UserNotFoundException;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.service.UserService;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public User findMe(String username) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("El usuario no existe");
        }
        return user.get();
    }

}
