package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.interfaces.rest.dto.account.AccountDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.account.AccountInfoDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.account.NewAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BillMapper.class)
public interface AccountMapper {

    @Mapping(source = "user.id", target = "user")
    AccountDTO toAccountDTO(Account account);

    @Mapping(source = "initialBalance", target = "currentBalance")
    Account toAccount(NewAccountDTO newAccountDTO);

    @Mapping(source = "user.id", target = "user")
    AccountInfoDTO toAccountInfoDTO(Account account);

    List<AccountDTO> toDTOList(List<Account> accounts);

}
