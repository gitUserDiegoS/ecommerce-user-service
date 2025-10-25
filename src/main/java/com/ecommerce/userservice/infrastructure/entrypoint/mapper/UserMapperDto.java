package com.ecommerce.userservice.infrastructure.entrypoint.mapper;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.CreateUserDto;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.UserFoundResponseDto;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperDto {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "documentId", target = "documentId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "mobile", target = "mobile")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "roleId", target = "roleId")
    @Mapping(source = "password", target = "password")
    User toModel(CreateUserDto createUserDto);

    UserResponseDto toResponse(User user);

    UserFoundResponseDto toFoundResponse(User user);


}
