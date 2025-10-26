package com.ecommerce.userservice.domain.usecase.auth;


import com.ecommerce.userservice.domain.model.tokenprovider.TokenProvider;

public interface IauthUseCase {

    TokenProvider login(String email, String password);

}
