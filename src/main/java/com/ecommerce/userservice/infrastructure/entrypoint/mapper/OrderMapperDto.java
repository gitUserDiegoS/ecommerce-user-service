package com.ecommerce.userservice.infrastructure.entrypoint.mapper;

import com.ecommerce.userservice.domain.model.order.Order;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.OrderSummaryDto;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapperDto {


    OrderSummaryDto toSummaryDto(Order order);

    List<OrderSummaryDto> toSummaryList(List<Order> orders);
}
