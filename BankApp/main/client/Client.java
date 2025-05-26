package com.example.bankApp.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private int id;
    private String social_id;
    private double balance;
    private int client_number;
}
