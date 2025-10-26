package com.ecommerce.userservice.domain.usecase.exception;


import com.ecommerce.userservice.domain.bussinesexception.BusinessException;
import com.ecommerce.userservice.domain.bussinesexception.ErrorCodes;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException(String message) {
        super(message, ErrorCodes.UNAUTHORIZED);
    }
}
