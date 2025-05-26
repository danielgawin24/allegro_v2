package com.example.tokenapp.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String value) {
        super(value);
    }
}
