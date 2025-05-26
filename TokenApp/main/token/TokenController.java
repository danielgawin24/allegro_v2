package com.example.tokenapp.token;

import com.example.tokenapp.request.UsernameRequest;
import com.example.tokenapp.response.AllegroTokenResponse;
import com.example.tokenapp.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final UserService userService;

    public TokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/generate")
    public ResponseEntity<AllegroTokenResponse> verifyUsername(@RequestBody UsernameRequest request) {
        AllegroTokenResponse response = userService.verifyUsername(request.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
