package com.example.tokenapp.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String token;

    @Column(name = "token_created_at")
    private LocalDateTime tokenCreatedAt;
}
