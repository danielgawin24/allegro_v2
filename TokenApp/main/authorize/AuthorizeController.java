package com.example.tokenapp.authorize;

import com.example.tokenapp.request.TokenRequest;
import com.example.tokenapp.response.BankTokenResponse;
import com.example.tokenapp.token.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizeController {

    private final TokenService tokenService;

    public AuthorizeController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/verifyToken")
    public ResponseEntity<BankTokenResponse> verifyToken(@RequestBody TokenRequest request) {
        BankTokenResponse response = tokenService.verifyToken(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
