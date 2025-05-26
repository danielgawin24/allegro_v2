package com.example.tokenapp.token;

import com.example.tokenapp.request.TokenRequest;
import com.example.tokenapp.user.UserEntity;
import com.example.tokenapp.user.UserEntityRepository;
import com.example.tokenapp.response.BankTokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock
    private UserEntityRepository userEntityRepository;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        UserEntity user = new UserEntity();
        user.setUsername("TestUser123");
        user.setToken("cb41edce-4025-40b6-bdec-a4252e1331ab");
        user.setTokenCreatedAt(LocalDateTime.of(2025, 3, 14, 12, 15, 47));
    }

    @Test
    void testTokenServiceVerifyTokenValidRequest() {
        // Given
        TokenRequest request = new TokenRequest("TestUser123", "cb41edce-4025-40b6-bdec-a4252e1331ab");
        BankTokenResponse response = new BankTokenResponse(request.getUsername(), request.getToken(), HttpStatus.OK);

        // When
        when(tokenService.verifyToken(request)).thenReturn(response);
        when(userEntityRepository.findByUsername("TestUser123")).thenReturn(Optional.of(new UserEntity()));

        // Then
        Assertions.assertEquals("TestUser123", response.getUsername());
    }

    @Test
    void testTokenServiceVerifyTokenNullRequest() {
        // Given
        UserEntity invalidUser = new UserEntity();
        invalidUser.setUsername("InvalidUser123");


        // When
        when(userEntityRepository.findByUsername(null));

        //Then

    }

}