package com.ecommerce.userservice.infrastructure.entrypoint;


import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.usecase.UserUseCase;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.CreateUserDto;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.UserResponseDto;
import com.ecommerce.userservice.infrastructure.entrypoint.mapper.UserMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase service;

    private final UserMapperDto userMapperDto;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody CreateUserDto userDto) {
        User user = userMapperDto.toModel(userDto);

        User save = service.save(user);

        UserResponseDto response = userMapperDto.toResponse(save);

        return ResponseEntity.ok()
                .body(response);
    }
}
