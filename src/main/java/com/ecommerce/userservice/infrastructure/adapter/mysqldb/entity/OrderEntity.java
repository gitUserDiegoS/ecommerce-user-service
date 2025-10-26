package com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_price")
    private Double totalPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
