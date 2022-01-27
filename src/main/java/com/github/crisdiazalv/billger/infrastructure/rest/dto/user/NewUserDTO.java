package com.github.crisdiazalv.billger.infrastructure.rest.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewUserDTO {

    private String name;
    private String identityDocument;
    private LocalDate birthday;
    private String email;
    private String username;
    private String password;

}
