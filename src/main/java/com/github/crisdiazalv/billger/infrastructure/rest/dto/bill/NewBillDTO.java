package com.github.crisdiazalv.billger.infrastructure.rest.dto.bill;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewBillDTO {

    private double amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;
    private String description;
    private String notes;
    private long category;
    private long account;

}
