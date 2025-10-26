package com.ecommerce.userservice.domain.model.passwordencoder.gateway;


/**
 * Define contract to encode and decode a password
 */
public interface PasswordEncoderRepository {

    String encode(String rawPassword);

    Boolean matches(String rawPassword, String encodedPassword);
}
