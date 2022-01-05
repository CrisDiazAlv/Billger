package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountInfoDTO {

    private long id;
    private long user;
    private long currentBalance;
    private String name;
    private String accountNumber;
    private LocalDateTime creationDate;

}
