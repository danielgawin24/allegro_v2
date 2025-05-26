package com.example.bankApp.feign;

import com.example.bankApp.request.TokenRequest;
import com.example.bankApp.response.BankTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "token-service2", url = "http://localhost:8083")
public interface TokenClient {

    @PostMapping("/auth/verifyToken")
    BankTokenResponse tokenResponse(@RequestBody TokenRequest tokenRequest);
}
