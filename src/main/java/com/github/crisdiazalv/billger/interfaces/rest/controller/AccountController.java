package com.github.crisdiazalv.billger.interfaces.rest.controller;

import com.github.crisdiazalv.billger.application.service.AccountService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.account.AccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.account.NewAccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    @Autowired
    public AccountController(AccountService service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accounts = mapper.toDTOList(service.findAll());
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable long id) {
        AccountDTO account = mapper.toAccountDTO(service.findById(id));
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NewAccountDTO account) {
        service.save(mapper.toAccount(account));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
