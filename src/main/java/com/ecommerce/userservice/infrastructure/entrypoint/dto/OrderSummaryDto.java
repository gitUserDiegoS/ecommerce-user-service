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
@Schema(description = "Response with ordes summary")
public class OrderSummaryDto {

    @Schema(description = "Order id", example = "1")
    private Long id;

    @Schema(description = "Order tracking number", example = "145454")
    private String orderTrackingNumber;

    @Schema(description = "Total price", example = "10000")
    private Double totalPrice;

    @Schema(description = "Status order", example = "PENDING")
    private String status;

}
