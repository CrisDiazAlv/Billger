package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewBillDTO {

    private long amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;
    private String description;
    private String notes;
    private long category;
    private long account;

}
