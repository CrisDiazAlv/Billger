package com.github.crisdiazalv.billger.infrastructure.rest.dto.account;

import lombok.Data;

@Data
public class NewAccountDTO {

    private double initialBalance;
    private String name;
    private String accountNumber;

}
