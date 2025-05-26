package com.example.tokenapp.token;

import com.example.tokenapp.request.TokenRequest;
import com.example.tokenapp.exception.InvalidTokenException;
import com.example.tokenapp.exception.UserNotFoundException;
import com.example.tokenapp.user.UserEntity;
import com.example.tokenapp.user.UserEntityRepository;
import com.example.tokenapp.response.BankTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final UserEntityRepository userEntityRepository;

    public TokenService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }
    // mock w unit testach (org.mockito)

    public BankTokenResponse verifyToken(TokenRequest request) {
        String requestUsername = request.getUsername();
        UserEntity user = userEntityRepository.findByUsername(requestUsername)
                .orElseThrow(() -> new UserNotFoundException(requestUsername));

        if (!request.getToken().equals(user.getToken())) {
            throw new InvalidTokenException("Invalid token: " + request.getToken());
        }

        return new BankTokenResponse(user.getUsername(), request.getToken(), HttpStatus.OK);
    }

}
