package com.ecommerce.userservice.domain.usecase.user;


import com.ecommerce.userservice.domain.bussinesexception.ExceptionMessages;
import com.ecommerce.userservice.domain.model.passwordencoder.gateway.PasswordEncoderRepository;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;

import com.ecommerce.userservice.domain.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UseCase for handling user operations
 */
@Service
@RequiredArgsConstructor
public class UserUseCase implements IuserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoderRepository passwordEncoder;


    /**
     * Save a new {@link User}
     *
     * @param user the user that would be created
     * @return user created
     */
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.register(user);
    }

    /**
     * Retrieves basic data with placed orders
     *
     * @param userId the Id user to query
     * @return {@link User} with profile data
     */
    public User getUserProfile(Long userId) {

        return userRepository.findByIdWithOrders(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND_EXCEPTION));

    }

}
