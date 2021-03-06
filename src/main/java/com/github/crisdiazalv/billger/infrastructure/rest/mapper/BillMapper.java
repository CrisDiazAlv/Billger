package com.github.crisdiazalv.billger.infrastructure.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.infrastructure.rest.dto.bill.BillDTO;
import com.github.crisdiazalv.billger.infrastructure.rest.dto.bill.NewBillDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface BillMapper {

    @Mapping(source = "account.id", target = "account")
    BillDTO toBillDTO(Bill bill);

    @Mapping(source = "account", target = "account.id")
    @Mapping(source = "category", target = "category.id")
    Bill toBill(NewBillDTO newBillDTO);

    List<BillDTO> toDTOList(List<Bill> bills);

}
