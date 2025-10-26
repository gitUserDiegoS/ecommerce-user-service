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
@Schema(description = "Response with the user found by email")
public class UserProfileResponseDto {
    private Long id;
    private String name;
    private String email;
    private String roleId;
    private List<OrderSummaryDto> orders;

}
