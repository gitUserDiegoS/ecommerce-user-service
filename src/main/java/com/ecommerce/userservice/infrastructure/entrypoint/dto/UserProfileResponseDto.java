package com.ecommerce.userservice.infrastructure.entrypoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response with the user's basic data")
public class UserProfileResponseDto {

    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User name", example = "Jhon")
    private String name;

    @Schema(description = "User email", example = "a@a.com")
    private String email;

    @Schema(description = "User role id", example = "1")
    private String roleId;

    @Schema(description = "List of orders", example = "List Order Summary")
    private List<OrderSummaryDto> orders;

}
