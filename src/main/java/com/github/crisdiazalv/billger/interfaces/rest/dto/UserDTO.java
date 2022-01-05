package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

    private long id;
    private String username;
    private String name;
    private String identityDocument;
    private LocalDate birthday;
    private String email;
    private List<AccountInfoDTO> accounts;

}
