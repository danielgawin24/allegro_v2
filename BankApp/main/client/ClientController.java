package com.example.bankApp.client;

import com.example.bankApp.request.BankRequest;
import com.example.bankApp.response.AllegroBankResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/verify")
    public ResponseEntity<AllegroBankResponse> verify(@RequestBody BankRequest request) {
        AllegroBankResponse response = clientService.verify(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
