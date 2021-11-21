package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AccountDTO {

    private long id;
    private long currentBalance;
    private long estimatedBalance;
    private String name;
    private String accountNumber;
    private String identityDocument;
    private LocalDate birthday;
    private String email;

    private List<BillDTO> bills;
}
