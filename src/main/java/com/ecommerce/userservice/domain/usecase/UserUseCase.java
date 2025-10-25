package com.ecommerce.userservice.domain.usecase;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserUseCase implements IuserUseCase{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.register(user);    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
