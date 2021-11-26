package com.github.crisdiazalv.billger.interfaces.rest.dto;

        import lombok.AllArgsConstructor;
        import lombok.Data;

        import java.time.LocalDate;
        import java.util.List;

@Data
@AllArgsConstructor
public class GroupedByDateBillDTO {

    private LocalDate date;
    private List<BillDTO> bills;
}
