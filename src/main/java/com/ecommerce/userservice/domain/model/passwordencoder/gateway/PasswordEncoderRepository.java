package com.ecommerce.userservice.domain.model.passwordencoder.gateway;


public interface PasswordEncoderRepository {

    String encode(String rawPassword);

    //Boolean matches(String rawPassword, String encodedPassword);
}
