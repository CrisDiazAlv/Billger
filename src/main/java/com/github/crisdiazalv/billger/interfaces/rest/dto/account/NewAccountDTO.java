package com.github.crisdiazalv.billger.interfaces.rest.dto.account;

import lombok.Data;

@Data
public class NewAccountDTO {

    private long initialBalance;
    private String name;
    private String accountNumber;

}
