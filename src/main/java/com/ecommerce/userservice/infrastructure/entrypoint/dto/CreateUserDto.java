package com.ecommerce.userservice.infrastructure.entrypoint.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request to create a new user")
public class CreateUserDto {

    @Schema(description = "User documentId", example = "1234567")
    private String documentId;

    @Schema(description = "User name", example = "Jhon")
    private String name;

    @Schema(description = "User lastname", example = "Dhoe")
    private String lastname;

    @Schema(description = "User mobile", example = "3197899685")
    private String mobile;

    @Email(message = "Email should have a valid format")
    @Schema(description = "User email", example = "correo@gmail.com.co")
    private String email;

    @Schema(description = "User role id", example = "1")
    private Long roleId;

    @Schema(description = "User password", example = "pa@s7Word")
    private String password;
}
