package com.ecommerce.userservice.domain.model.user.gateway;


import com.ecommerce.userservice.domain.model.user.User;

public interface UserRepository {

    User register(User user);

    //Optional<User> findByEmail(String email);

}
