package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotAvailableException.class)
    public ResponseEntity<ErrorModel> handleUsernameNotAvailableException(UsernameNotAvailableException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorModel> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorModel> handleConnectException(ConnectException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
