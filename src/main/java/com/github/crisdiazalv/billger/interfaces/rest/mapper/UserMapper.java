package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.User;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewUserDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(NewUserDTO user);

    User toUser(UserDTO userDTO);


}
