package com.ecommerce.userservice.domain.model.tokenprovider.gateway;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.user.User;
import com.ecommerce.userservice.domain.model.usersession.UserSession;

/**
 * Defines a contract to generate and validate jwt tokens
 */
public interface TokenProviderRepository {

    TokenProvider generateToken(User user);

    UserSession validateToken(String token);
}
