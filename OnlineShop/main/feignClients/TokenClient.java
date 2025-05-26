package com.example.demo.feignClients;

import com.example.demo.request.UsernameRequest;
import com.example.demo.response.AllegroTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "token-service", url = "http://localhost:8083")
public interface TokenClient {

    @PostMapping("/token/generate")
    AllegroTokenResponse verifyUsername(@RequestBody UsernameRequest request);

    @PostMapping("/user/register")
    ResponseEntity<String> registerUser(@RequestBody UsernameRequest request);
}
