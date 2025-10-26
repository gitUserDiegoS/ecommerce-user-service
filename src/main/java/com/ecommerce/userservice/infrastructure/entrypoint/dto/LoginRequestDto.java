package com.ecommerce.userservice.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Used for request login")
public class LoginRequestDto {

    @Schema(description = "User email", example = "correo@gmail.com.co")
    private String email;

    @Schema(description = "User password", example = "pa@s7Word")
    private String password;

}
