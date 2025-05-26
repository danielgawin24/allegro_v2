package com.example.bankApp.exception;

public class TokenAppErrorException extends RuntimeException {
    public TokenAppErrorException(String message) {
        super(message);
    }
}
