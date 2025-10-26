package com.ecommerce.userservice.domain.usecase.exception;


import com.ecommerce.userservice.domain.bussinesexception.BusinessException;
import com.ecommerce.userservice.domain.bussinesexception.ErrorCodes;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message, ErrorCodes.NOT_FOUND);
    }
}
