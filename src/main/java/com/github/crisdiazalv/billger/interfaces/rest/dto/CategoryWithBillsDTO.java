package com.github.crisdiazalv.billger.interfaces.rest.dto;

import com.github.crisdiazalv.billger.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryWithBillsDTO {

    private Category category;
    private List<BillDTO> bills;
    private long total;

}
