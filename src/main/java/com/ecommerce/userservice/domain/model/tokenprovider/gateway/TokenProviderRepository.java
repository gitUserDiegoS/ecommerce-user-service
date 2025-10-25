package com.ecommerce.userservice.domain.model.tokenprovider.gateway;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;
import com.ecommerce.userservice.domain.model.user.User;

public interface TokenProviderRepository {

    TokenProvider generateToken(User user);

//    UserSession validateToken(String token);
}
