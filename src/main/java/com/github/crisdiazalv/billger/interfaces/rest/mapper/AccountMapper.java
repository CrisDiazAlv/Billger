package com.github.crisdiazalv.billger.interfaces.rest.mapper;


import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.interfaces.rest.dto.AccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = BillMapper.class)
public interface AccountMapper {

    AccountDTO toAccountDTO(Account account);

    Account toAccount(AccountDTO accountDTO);

    List<AccountDTO> toDTOList(List<Account> accounts);
}
