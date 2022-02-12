package com.github.crisdiazalv.billger.infrastructure.rest.dto.account;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountInfoDTO {

    private long id;
    private long user;
    private double currentBalance;
    private String name;
    private String accountNumber;
    private LocalDateTime creationDate;

}
