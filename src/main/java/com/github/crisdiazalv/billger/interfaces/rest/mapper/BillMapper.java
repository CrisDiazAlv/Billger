package com.github.crisdiazalv.billger.interfaces.rest.mapper;


import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface BillMapper {

    @Mapping(target = "account", source = "account.id")
    BillDTO toBillDTO(Bill bill);

    @Mapping(source = "account", target = "account.id")
    Bill toBill(BillDTO billDTO);

    List<BillDTO> toDTOList(List<Bill> bills);
}
