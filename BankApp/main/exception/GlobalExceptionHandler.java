package com.example.bankApp.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorModel> handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenResponseException.class)
    public ResponseEntity<ErrorModel> handleInvalidTokenResponseException(InvalidTokenResponseException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenAppErrorException.class)
    public ResponseEntity<ErrorModel> handleTokenAppErrorException(TokenAppErrorException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorModel> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
