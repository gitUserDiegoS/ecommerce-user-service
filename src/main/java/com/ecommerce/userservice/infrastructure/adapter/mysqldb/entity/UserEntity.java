package com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id")
    private String documentId;

    private String name;

    private String lastname;

    private String mobile;

    private String email;

    @Column(name = "role_id")
    private Long roleId;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();


}