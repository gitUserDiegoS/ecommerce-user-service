package com.ecommerce.userservice.domain.usecase.auth;


import com.ecommerce.userservice.domain.bussinesexception.ExceptionMessages;
import com.ecommerce.userservice.domain.model.passwordencoder.gateway.PasswordEncoderRepository;
import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.tokenprovider.gateway.TokenProviderRepository;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.user.gateway.UserRepository;
import com.ecommerce.userservice.domain.usecase.exception.InvalidCredentialsException;
import com.ecommerce.userservice.domain.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthUseCase implements IauthUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoderRepository passwordEncoder;

    private final TokenProviderRepository tokenProviderRepository;

    @Override
    public TokenProvider login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND_EXCEPTION));

        if (Boolean.FALSE.equals(passwordEncoder.matches(rawPassword, user.getPassword()))) {
            throw new InvalidCredentialsException(ExceptionMessages.INVALID_CREDENTIAL_EXCEPTION);
        }

        return tokenProviderRepository.generateToken(user);
    }

}
