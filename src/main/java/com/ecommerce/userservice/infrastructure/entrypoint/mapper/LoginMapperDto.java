package com.ecommerce.userservice.infrastructure.entrypoint.mapper;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.LoginResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapperDto {

   LoginResponseDto toResponse(TokenProvider tokenProvider);

}
