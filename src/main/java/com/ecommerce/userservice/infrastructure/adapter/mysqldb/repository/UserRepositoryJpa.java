package com.ecommerce.userservice.infrastructure.adapter.mysqldb.repository;


import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
