package com.ecommerce.userservice.infrastructure.adapter.entrypoint;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }
}
