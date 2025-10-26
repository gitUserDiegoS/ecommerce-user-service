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
@Schema(description = "Response with the user found by email")
public class OrderSummaryDto {

    private Long id;
    private String orderTrackingNumber;
    private Double totalPrice;
    private String status;

}
