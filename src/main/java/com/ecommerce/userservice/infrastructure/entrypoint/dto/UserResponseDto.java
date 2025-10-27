package com.ecommerce.userservice.infrastructure.entrypoint.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response for a user created")
public class UserResponseDto {

    @Schema(description = "id for a user created", example = "21")
    private Long id;

}
