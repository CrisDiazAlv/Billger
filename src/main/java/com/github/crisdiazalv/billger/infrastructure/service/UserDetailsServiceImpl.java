package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.BillNotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.domain.model.UserPrincipal;
import com.github.crisdiazalv.billger.infrastructure.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new BillNotFoundException("El usuario no existe");
        }
        return new UserPrincipal(user.get());
    }

}
