package com.ecommerce.userservice.infrastructure.entrypoint.exceptionhandler;


import com.ecommerce.userservice.infrastructure.entrypoint.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;


import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex, ServerWebExchange exchange) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ErrorType.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionAcceddDenied(
            AccessDeniedException ex, ServerWebExchange exchange) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ErrorType.FORBIDDEN.name(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionNotAuthenticated(
            AuthenticationException ex, ServerWebExchange exchange) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ErrorType.UNAUTHORIZED.name(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleBusinessException(
            BusinessException ex, ServerWebExchange exchange) {

        HttpStatus status = ErrorType.fromCode(ex.getErrorCode());

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ex.getErrorCode(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return Mono.just(ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse));
    }

    @ExceptionHandler(ValidationException.class)
    public Mono<ResponseEntity<ErrorResponseDto>> handleValidationException(
            ValidationException ex, ServerWebExchange exchange) {

        HttpStatus status = ErrorType.fromCode(ex.getErrorCode());

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ex.getErrorCode(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return Mono.just(ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse));
    }


}



