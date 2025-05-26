package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyRegisteredException;
import com.example.demo.register.RegisterForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserEntityRepository userEntityRepository;

    public UserService(PasswordEncoder passwordEncoder, UserEntityRepository userEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userEntityRepository = userEntityRepository;
    }

    public void registerUser(RegisterForm form) {
        //Add password requirements in forms of: UPPERCASE LETTER, special symbol !*$%, and numbers 123
        Optional<UserEntity> userEntity = userEntityRepository.findByUsername(form.getUsername());
        if (userEntity.isPresent()) {
            throw new UserAlreadyRegisteredException("User already registered: " + form.getUsername());
        }
        UserEntity newUser = new UserEntity();
        newUser.setUsername(form.getUsername());
        newUser.setPassword(passwordEncoder.encode(form.getPassword()));
        userEntityRepository.save(newUser);
    }
}
