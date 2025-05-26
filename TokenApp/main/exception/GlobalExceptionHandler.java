package com.example.tokenapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ErrorModel> handleInvalidUsernameException(InvalidUsernameException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorModel> handleInvalidTokenException(InvalidTokenException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
        return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.CONFLICT);
    }
}
