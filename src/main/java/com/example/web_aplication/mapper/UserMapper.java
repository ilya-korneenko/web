package com.example.web_aplication.mapper;

import com.example.web_aplication.dto.UserDTO;
import com.example.web_aplication.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
