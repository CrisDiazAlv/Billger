package com.github.crisdiazalv.billger.interfaces.rest.dto.category;

import com.github.crisdiazalv.billger.interfaces.rest.dto.bill.BillDTO;
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
