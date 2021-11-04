package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Account account) {
        service.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }


}

