package com.github.crisdiazalv.billger.infrastructure.rest.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class BillsGroupedByDateDTO {

    private LocalDate date;
    private List<BillDTO> bills;

}
