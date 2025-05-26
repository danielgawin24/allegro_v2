package com.example.demo.authorize;

import com.example.demo.feignClients.BankClient;
import com.example.demo.feignClients.TokenClient;
import com.example.demo.user.UserEntity;
import com.example.demo.request.BankRequest;
import com.example.demo.request.UsernameRequest;
import com.example.demo.response.AllegroBankResponse;
import com.example.demo.response.AllegroTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TokenClient tokenClient;
    private final BankClient bankClient;

    public AuthService(TokenClient tokenClient, BankClient bankClient) {
        this.tokenClient = tokenClient;
        this.bankClient = bankClient;
    }

    public ResponseEntity<?> verifyPurchase() {
        // uzywac tylko jak user jest zalogowany = ale zawsze jest zalogowany wysyłając ten request
        UserEntity principal = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AllegroTokenResponse tokenResponse = tokenClient.verifyUsername(new UsernameRequest(principal.getUsername()));

        if (tokenResponse.getStatus() == null) {
            return new ResponseEntity<>("Invalid status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tokenResponse.getStatus().is2xxSuccessful()) {
            double cartValue = 9.99d;
            BankRequest request = new BankRequest(principal.getUsername(), cartValue, tokenResponse.getToken());
            AllegroBankResponse bankResponse = bankClient.verifyClient(request);

            return new ResponseEntity<>(bankResponse, bankResponse.getStatus());
        }
        if (tokenResponse.getStatus().is4xxClientError()) {
            return check400Status(tokenResponse.getStatus());
        }
        if (tokenResponse.getStatus().is5xxServerError()) {
            return new ResponseEntity<>("TokenApp Server Error", tokenResponse.getStatus());
        }
        return new ResponseEntity<>("Service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<?> check400Status(HttpStatus status) {
        switch (status) {
            case BAD_REQUEST:
                return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
            case UNAUTHORIZED:
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            case FORBIDDEN:
                return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
            case NOT_FOUND:
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<>("Client error", status);
        }
    }
}
