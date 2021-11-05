package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {

    private long id;
    private long currentBalance;
    private long estimatedBalance;

    private List<BillDTO> bills;
}
