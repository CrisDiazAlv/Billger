package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public void save(@RequestBody Account account) {
        service.save(account);
    }


}

