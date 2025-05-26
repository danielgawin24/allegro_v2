package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankRequest {

    private String username;
    private double amount;
    private String token;
}
