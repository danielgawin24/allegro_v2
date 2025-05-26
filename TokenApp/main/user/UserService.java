package com.example.tokenapp.user;

import com.example.tokenapp.exception.UserAlreadyRegisteredException;
import com.example.tokenapp.response.AllegroTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public UserService(UserEntityRepository userRepository) {
        this.userEntityRepository = userRepository;
    }

    public AllegroTokenResponse verifyUsername(String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseGet(() -> registerUser(username));

        if (user.getToken() == null || isTokenExpired(user.getTokenCreatedAt())) {
            assignNewToken(user);
        }
        userEntityRepository.save(user);
        return new AllegroTokenResponse(user.getUsername(), user.getToken(), HttpStatus.OK);
    }

    public UserEntity registerUser(String username) {
        Optional<UserEntity> userEntity = userEntityRepository.findByUsername(username);
        if (userEntity.isPresent()) {
            throw new UserAlreadyRegisteredException("User already registered: " + username);
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        userEntityRepository.save(user);
        return user;
    }

    private void assignNewToken(UserEntity user) {
        user.setToken(generateToken());
        user.setTokenCreatedAt(generateTokenCreationDate());
    }

    private boolean isTokenExpired(LocalDateTime tokenCreationDate) {
        return Duration.between(tokenCreationDate, LocalDateTime.now()).toMinutes() > 15;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private LocalDateTime generateTokenCreationDate() {
        return LocalDateTime.now();
    }
}
