package com.ecommerce.userservice.infrastructure.adapter.mysqldb.repository;


import com.ecommerce.userservice.infrastructure.adapter.mysqldb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


/**
 * Repository interface for performing CRUD operations on {@link UserEntity}
 */
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

    /**
     * Find user by email when the user is saved in order to accurately determine if the user doesn't exist
     *
     * @param email the email used in the query
     * @return the {@link UserEntity} found
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Retrieves a {@link UserEntity} and its associated order data while optimizing the fetching process.
     *
     * @param userId the user id to be queried
     * @return the {@link UserEntity}
     */
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.orders WHERE u.id = :userId")
    Optional<UserEntity> findByIdWithOrders(@Param("userId") Long userId);
}
