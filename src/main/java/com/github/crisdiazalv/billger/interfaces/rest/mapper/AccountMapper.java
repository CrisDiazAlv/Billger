package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.interfaces.rest.dto.AccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BillMapper.class)
public interface AccountMapper {

    @Mapping(source = "user.id", target = "user")
    AccountDTO toAccountDTO(Account account);

    @Mapping(source = "user", target = "user.id")
    Account toAccount(AccountDTO accountDTO);

    @Mapping(source = "initialBalance", target = "currentBalance")
    Account toAccount(NewAccountDTO accountDTO);

    List<AccountDTO> toDTOList(List<Account> accounts);

}
