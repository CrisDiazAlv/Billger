package com.github.crisdiazalv.billger.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryWithBillsDTO {

    private CategoryDTO category;
    private List<BillDTO> bills;
    private long total;

}
