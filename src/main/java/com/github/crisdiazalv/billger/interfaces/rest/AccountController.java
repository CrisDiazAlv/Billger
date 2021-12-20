package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.service.AccountService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.AccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewAccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable long id) {
        AccountDTO account = mapper.toAccountDTO(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NewAccountDTO account) {
        service.save(mapper.toAccount(account));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
