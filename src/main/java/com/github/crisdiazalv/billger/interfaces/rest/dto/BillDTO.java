package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDTO {

    private long id;
    private long amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;
    private String description;
    private String notes;
    private CategoryDTO category;
    private long account;

}
