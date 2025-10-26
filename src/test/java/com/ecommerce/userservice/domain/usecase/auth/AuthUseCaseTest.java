package com.ecommerce.userservice.domain.usecase.auth;

import com.ecommerce.userservice.domain.bussinesexception.ExceptionMessages;
import com.ecommerce.userservice.domain.model.passwordencoder.gateway.PasswordEncoderRepository;
import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.tokenprovider.gateway.TokenProviderRepository;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
import com.ecommerce.userservice.domain.usecase.exception.InvalidCredentialsException;
import com.ecommerce.userservice.domain.usecase.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AuthUseCase}
 */
class AuthUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoderRepository passwordEncoder;

    @Mock
    private TokenProviderRepository tokenProviderRepository;

    @InjectMocks
    private AuthUseCase authUseCase;

    private User user;
    private TokenProvider token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .email("john@example.com")
                .password("encodedPass")
                .name("John")
                .build();

        token = TokenProvider.builder()
                .token("sdsdsd5454844")
                .build();
    }


    @Test
    void shouldLoginSuccessfully() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("rawPassword", "encodedPass")).thenReturn(true);
        when(tokenProviderRepository.generateToken(any(User.class))).thenReturn(token);

        TokenProvider result = authUseCase.login("john@example.com", "rawPassword");

        assertNotNull(result);
        assertEquals("sdsdsd5454844", result.getToken());
        verify(userRepository, times(1)).findByEmail("john@example.com");
        verify(passwordEncoder, times(1)).matches("rawPassword", "encodedPass");
        verify(tokenProviderRepository, times(1)).generateToken(user);
    }


    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotExists() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(
                UserNotFoundException.class,
                () -> authUseCase.login("notfound@example.com", "pass")
        );

        assertEquals(ExceptionMessages.USER_NOT_FOUND_EXCEPTION, ex.getMessage());
        verify(userRepository, times(1)).findByEmail("notfound@example.com");
        verify(passwordEncoder, never()).matches(any(), any());
        verify(tokenProviderRepository, never()).generateToken(any());
    }


    @Test
    void shouldThrowInvalidCredentialsWhenPasswordNotMatch() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPass", "encodedPass")).thenReturn(false);

        InvalidCredentialsException ex = assertThrows(
                InvalidCredentialsException.class,
                () -> authUseCase.login("john@example.com", "wrongPass")
        );

        assertEquals(ExceptionMessages.INVALID_CREDENTIAL_EXCEPTION, ex.getMessage());
        verify(userRepository, times(1)).findByEmail("john@example.com");
        verify(passwordEncoder, times(1)).matches("wrongPass", "encodedPass");
        verify(tokenProviderRepository, never()).generateToken(any());
    }
}
