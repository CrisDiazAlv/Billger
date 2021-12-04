package com.github.crisdiazalv.billger.interfaces.rest;


import com.github.crisdiazalv.billger.domain.service.UserService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.AccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewUserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.UserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.AccountMapper;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;
    private final AccountMapper mapperAccount;

    @Autowired
    public UserController(UserService service, UserMapper mapper, AccountMapper mapperAccount) {
        this.service = service;
        this.mapper = mapper;
        this.mapperAccount = mapperAccount;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NewUserDTO user) {
        service.save(mapper.toUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable long id, @RequestBody AccountDTO account) {
        service.addAccount(id, mapperAccount.toAccount(account));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
        UserDTO user = mapper.toUserDTO(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
