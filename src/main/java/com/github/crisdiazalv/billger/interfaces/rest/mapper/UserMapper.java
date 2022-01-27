package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.interfaces.rest.dto.user.NewUserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.user.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(NewUserDTO newUserDTO);

}
