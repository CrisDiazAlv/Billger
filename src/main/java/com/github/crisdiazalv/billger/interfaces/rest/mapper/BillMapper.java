package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewBillDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface BillMapper {

    @Mapping(source = "account.id", target = "account")
    BillDTO toBillDTO(Bill bill);

    @Mapping(source = "account", target = "account.id")
    Bill toBill(BillDTO billDTO);

    @Mapping(source = "account", target = "account.id")
    @Mapping(source = "category", target = "category.id")
    Bill toBill(NewBillDTO newBillDTO);

    List<BillDTO> toDTOList(List<Bill> bills);

}
