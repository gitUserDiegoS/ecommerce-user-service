package com.ecommerce.userservice.domain.usecase.user;


import com.ecommerce.userservice.domain.model.user.User;

import java.util.Optional;

public interface IuserUseCase {

    User save(User user);

    User getUserProfile(Long userId);
}
