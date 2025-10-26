package com.ecommerce.userservice.domain.usecase.infrastructure.adapter.entrypoint;

import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.usersession.UserSession;
import com.ecommerce.userservice.domain.usecase.auth.IauthUseCase;
import com.ecommerce.userservice.domain.usecase.user.IuserUseCase;
import com.ecommerce.userservice.infrastructure.adapter.securityauth.JwtAuthFilter;

import com.ecommerce.userservice.infrastructure.entrypoint.UserController;
import com.ecommerce.userservice.infrastructure.entrypoint.dto.*;
import com.ecommerce.userservice.infrastructure.entrypoint.mapper.LoginMapperDto;
import com.ecommerce.userservice.infrastructure.entrypoint.mapper.UserMapperDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JwtAuthFilter.class)
        })
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IuserUseCase userUseCase;

    @MockitoBean
    private IauthUseCase authUseCase;

    @MockitoBean
    private UserMapperDto userMapperDto;

    @MockitoBean
    private LoginMapperDto loginMapperDto;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("test@example.com")
                .name("John")
                .lastname("Doe")
                .password("12345")
                .roleId(1L)
                .build();

        User user = User.builder().id(1L).email("test@example.com").build();
        UserResponseDto responseDto = UserResponseDto.builder().id(1L).build();

        Mockito.when(userMapperDto.toModel(any())).thenReturn(user);
        Mockito.when(userUseCase.save(any())).thenReturn(user);
        Mockito.when(userMapperDto.toResponse(any())).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    void shouldLoginSuccessfully() throws Exception {
        LoginRequestDto loginRequest = new LoginRequestDto("user@example.com", "password");

        TokenProvider token = TokenProvider.builder().token("jwtToken123").type("Bearer").expires(360000L).build();

        LoginResponseDto response = LoginResponseDto.builder().token("jwtToken123").type("Bearer").expires(360000L).build();

        Mockito.when(authUseCase.login(any(), any())).thenReturn(token);
        Mockito.when(loginMapperDto.toResponse(token)).thenReturn(response);

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwtToken123"));
    }


    @Test
    void shouldFailReturnUserProfile() throws Exception {
        UserSession session = new UserSession(1L, "user@example.com", "CUSTOMER");
        User user = User.builder().id(1L).email("user@example.com").name("John").build();
        UserProfileResponseDto profile = UserProfileResponseDto.builder()
                .email("user@example.com")
                .name("John")
                .build();

        Mockito.when(userUseCase.getUserProfile(1L)).thenReturn(user);
        Mockito.when(userMapperDto.toProfileResponse(any())).thenReturn(profile);

        mockMvc.perform(get("/api/v1/users/userprofile")
                        .principal(() -> "user@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

    }
}