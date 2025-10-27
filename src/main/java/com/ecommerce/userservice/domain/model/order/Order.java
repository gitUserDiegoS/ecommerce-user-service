package com.ecommerce.userservice.domain.model.order;

import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private Long id;
    private String orderTrackingNumber;
    private Double totalPrice;
    private String status;
    private UserEntity user;
}
