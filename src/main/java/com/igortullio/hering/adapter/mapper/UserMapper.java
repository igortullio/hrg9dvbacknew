package com.igortullio.hering.adapter.mapper;

import com.igortullio.hering.adapter.database.entity.UserEntity;
import com.igortullio.hering.adapter.dto.input.UserDtoInput;
import com.igortullio.hering.adapter.dto.output.UserDtoOutput;
import com.igortullio.hering.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDtoOutput userToUserDtoOutput(User user);

    User userDtoInputToUser(UserDtoInput userDtoInput);

    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(User user);

}
