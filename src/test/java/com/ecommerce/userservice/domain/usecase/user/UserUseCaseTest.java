package com.ecommerce.userservice.domain.usecase.user;

import com.ecommerce.userservice.domain.bussinesexception.ExceptionMessages;
import com.ecommerce.userservice.domain.model.passwordencoder.gateway.PasswordEncoderRepository;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
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
 * Unit tests for {@link UserUseCase}
 */
class UserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoderRepository passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1L)
                .documentId("123456")
                .name("John")
                .lastname("Doe")
                .mobile("987654321")
                .email("john@example.com")
                .roleId(2L)
                .password("plainPass")
                .build();
    }


    @Test
    void shouldEncodePasswordAndSaveUser() {
        when(passwordEncoder.encode("plainPass")).thenReturn("encodedPass");
        when(userRepository.register(any(User.class))).thenReturn(user);

        User result = userUseCase.save(user);

        assertNotNull(result);
        verify(passwordEncoder, times(1)).encode("plainPass");
        verify(userRepository, times(1)).register(any(User.class));
        assertEquals("encodedPass", user.getPassword());
    }


    @Test
    void shouldReturnUserProfile() {
        when(userRepository.findByIdWithOrders(1L)).thenReturn(Optional.of(user));

        User result = userUseCase.getUserProfile(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepository, times(1)).findByIdWithOrders(1L);
    }


    @Test
    void shouldThrowUserNotFoundException() {
        when(userRepository.findByIdWithOrders(99L)).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(
                UserNotFoundException.class,
                () -> userUseCase.getUserProfile(99L)
        );

        assertEquals(ExceptionMessages.USER_NOT_FOUND_EXCEPTION, ex.getMessage());
        verify(userRepository, times(1)).findByIdWithOrders(99L);
    }
}
