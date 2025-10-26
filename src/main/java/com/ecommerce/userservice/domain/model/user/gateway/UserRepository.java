package com.ecommerce.userservice.domain.model.user.gateway;


import com.ecommerce.userservice.domain.model.user.User;

import java.util.Optional;

/**
 * Defines a contract to perform operations over a user
 */
public interface UserRepository {

    User register(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdWithOrders(Long userId);

}
