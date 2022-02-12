package com.github.crisdiazalv.billger.infrastructure.rest.dto.category;

import com.github.crisdiazalv.billger.infrastructure.rest.dto.bill.BillDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryWithBillsDTO {

    private CategoryDTO category;
    private List<BillDTO> bills;
    private double total;

}
