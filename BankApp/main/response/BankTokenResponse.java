package com.example.bankApp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTokenResponse {
    private String username;
    private String token;
    private HttpStatus status;
}
