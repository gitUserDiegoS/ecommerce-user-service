package com.ecommerce.userservice.domain.model.user;

import com.ecommerce.userservice.domain.model.order.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String documentId;
    private String name;
    private String lastname;
    private String mobile;
    private String email;
    private Long roleId;
    private String password;

    @Builder.Default
    private List<Order> orders = new ArrayList<>();

}
