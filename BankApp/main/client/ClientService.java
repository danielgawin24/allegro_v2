package com.example.bankApp.client;

import com.example.bankApp.request.BankRequest;
import com.example.bankApp.request.TokenRequest;
import com.example.bankApp.exception.*;
import com.example.bankApp.feign.TokenClient;
import com.example.bankApp.response.AllegroBankResponse;
import com.example.bankApp.response.BankTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientEntityRepository clientEntityRepository;
    private final TokenClient tokenClient;

    public ClientService(ClientEntityRepository clientEntityRepository, TokenClient tokenClient) {
        this.clientEntityRepository = clientEntityRepository;
        this.tokenClient = tokenClient;
    }

    public AllegroBankResponse verify(BankRequest request) {
//        throw new SQLException(); // checked
//        throw new NullPointerException(); // unchecked
//throw new InsufficientBalanceException("Nie stac cie");

        ClientEntity client = clientEntityRepository.findByUsername(request.getUsername())
                .orElseGet(() -> registerClient(request.getUsername(), request.getAmount()));

        if (request.getAmount() > client.getBalance()) {
            throw new InsufficientBalanceException("User " + request.getUsername() + " has insufficient balance.");
        }

        TokenRequest tokenRequest = new TokenRequest(request.getUsername(), request.getToken());
        BankTokenResponse bankTokenResponse = tokenClient.tokenResponse(tokenRequest);

        if (bankTokenResponse.getStatus().is4xxClientError()) {
            throw new InvalidCredentialsException("Invalid credentials for user " + request.getUsername());
        }
        if (bankTokenResponse.getStatus().is5xxServerError()) {
            throw new TokenAppErrorException("TokenApp is not working properly.");
        }

        client.setBalance(client.getBalance() - request.getAmount());
        clientEntityRepository.save(client);

        return new AllegroBankResponse(
                bankTokenResponse.getUsername(),
                request.getAmount(),
                bankTokenResponse.getToken(),
                HttpStatus.OK
        );
    }

    public ClientEntity registerClient(String username, double amount) {
        Optional<ClientEntity> clientEntity = clientEntityRepository.findByUsername(username);
        if (clientEntity.isPresent()) {
            throw new UserAlreadyRegisteredException("User already registered: " + username);
        }
        ClientEntity client = new ClientEntity();
        client.setUsername(username);
        client.setBalance(amount);
        clientEntityRepository.save(client);
        return client;
    }
}
