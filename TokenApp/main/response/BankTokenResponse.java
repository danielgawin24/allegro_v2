package com.example.tokenapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BankTokenResponse {

    private String username;
    private String token;
    private HttpStatus status;
}
