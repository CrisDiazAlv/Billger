package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.User;

public interface UserService {

    void signUp(User user);

    User findMe(String username);

}
