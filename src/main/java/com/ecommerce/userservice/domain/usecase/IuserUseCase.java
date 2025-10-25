package com.ecommerce.userservice.domain.usecase;



import com.ecommerce.userservice.domain.model.user.User;

import java.util.Optional;

public interface IuserUseCase {
    User save(User user);


    Optional<User> findByEmail(String email);
}
