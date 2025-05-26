package com.example.tokenapp.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String value) {
        super(value);
    }
}
