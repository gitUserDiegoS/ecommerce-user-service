package com.ecommerce.userservice.infrastructure.entrypoint;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.usersession.UserSession;

import com.ecommerce.userservice.domain.usecase.auth.IauthUseCase;
import com.ecommerce.userservice.domain.usecase.user.IuserUseCase;

import com.ecommerce.userservice.infrastructure.entrypoint.dto.*;
import com.ecommerce.userservice.infrastructure.entrypoint.mapper.LoginMapperDto;
import com.ecommerce.userservice.infrastructure.entrypoint.mapper.UserMapperDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for managing users, allows functionality such as registration, login, and offers security approaches
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IuserUseCase userUseCase;

    private final IauthUseCase authUseCase;

    private final UserMapperDto userMapperDto;

    private final LoginMapperDto loginMapperDto;


    /**
     * Creates a user based on their unique identifier and email
     *
     * @param userDto the user to create
     * @return The ID for a user's has been created.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody CreateUserDto userDto) {
        User user = userMapperDto.toModel(userDto);

        User save = userUseCase.save(user);

        UserResponseDto response = userMapperDto.toResponse(save);

        return ResponseEntity.ok()
                .body(response);
    }

    /**
     * Validates credentials for login
     *
     * @param request the {@link LoginRequestDto} with the credentials for login
     * @return the {@link LoginResponseDto} with token authorization data
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        log.info("Init login for user: {}", request.getEmail());
        TokenProvider token = authUseCase.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok()
                .body(loginMapperDto.toResponse(token));
    }

    /**
     * Retrieve information about the user who has logged in
     *
     * @param user the {@link UserSession} with the data of the user who has logged in
     * @return the {@link UserProfileResponseDto} with basic data and placed orders
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/userprofile")
    public ResponseEntity<UserProfileResponseDto> getProfile(@AuthenticationPrincipal UserSession user) {
        log.info("Get data from session for user id: {}", user);
        User userProfile = userUseCase.getUserProfile(user.getId());

        return ResponseEntity.ok()
                .body(userMapperDto.toProfileResponse(userProfile));
    }
}
