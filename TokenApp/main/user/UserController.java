package com.example.tokenapp.user;

import com.example.tokenapp.request.UsernameRequest;
import com.example.tokenapp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserEntityRepository userEntityRepository;

    public UserController(UserService userService, UserEntityRepository userEntityRepository) {
        this.userService = userService;
        this.userEntityRepository = userEntityRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsernameRequest request) {
        UserEntity user = userService.registerUser(request.getUsername());
        return new ResponseEntity<>("User registered: " + user.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
