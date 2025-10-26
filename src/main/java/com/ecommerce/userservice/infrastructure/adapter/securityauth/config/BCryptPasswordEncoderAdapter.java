package com.ecommerce.userservice.infrastructure.adapter.securityauth.config;


import com.ecommerce.userservice.domain.model.passwordencoder.gateway.PasswordEncoderRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Getter
@Setter
@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderRepository {

    private final BCryptPasswordEncoder delegate;

    public BCryptPasswordEncoderAdapter() {
        this.delegate = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public Boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);

    }


}
