package com.ecommerce.userservice.domain.model.user.gateway;


import com.ecommerce.userservice.domain.model.user.User;

import java.util.Optional;

public interface UserRepository {

    User register(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdWithOrders(Long userId);

}
