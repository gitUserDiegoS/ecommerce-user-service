package com.ecommerce.userservice.domain.usecase.user;


import com.ecommerce.userservice.domain.model.user.User;


public interface IuserUseCase {

    User save(User user);

    User getUserProfile(Long userId);
}
