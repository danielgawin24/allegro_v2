package com.example.demo.feignClients;

import com.example.demo.request.BankRequest;
import com.example.demo.response.AllegroBankResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="bank-service", url="http://localhost:8081")
public interface BankClient {

    @PostMapping("/client/verify")
    AllegroBankResponse verifyClient(@RequestBody BankRequest request);
}
