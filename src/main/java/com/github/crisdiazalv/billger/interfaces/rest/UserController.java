package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.service.UserService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewUserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.UserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody NewUserDTO user) {
        service.signUp(mapper.toUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> findMe(Principal principal) {
        UserDTO user = mapper.toUserDTO(service.findMe(principal.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
