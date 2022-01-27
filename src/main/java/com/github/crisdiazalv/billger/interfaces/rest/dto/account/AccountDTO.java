package com.github.crisdiazalv.billger.interfaces.rest.dto.account;

import com.github.crisdiazalv.billger.interfaces.rest.dto.bill.BillDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccountDTO {

    private long id;
    private long user;
    private long currentBalance;
    private String name;
    private String accountNumber;
    private LocalDateTime creationDate;
    private List<BillDTO> bills;

}
