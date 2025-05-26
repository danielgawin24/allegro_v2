package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllegroBankResponse {
    private String username;
    private double amount;
    private String token;
    private HttpStatus status;
}
