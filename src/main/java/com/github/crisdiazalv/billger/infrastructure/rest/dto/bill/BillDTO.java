package com.github.crisdiazalv.billger.infrastructure.rest.dto.bill;

import com.github.crisdiazalv.billger.infrastructure.rest.dto.category.CategoryDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDTO {

    private long id;
    private double amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;
    private String description;
    private String notes;
    private CategoryDTO category;
    private long account;

}
