package com.ecommerce.userservice.infrastructure.entrypoint.mapper;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.CreateUserDto;

import com.ecommerce.userservice.infrastructure.entrypoint.dto.UserProfileResponseDto;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.UserResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {OrderMapperDto.class})
public interface UserMapperDto {


    User toModel(CreateUserDto createUserDto);

    UserResponseDto toResponse(User user);


    UserProfileResponseDto toProfileResponse(User user);


}
