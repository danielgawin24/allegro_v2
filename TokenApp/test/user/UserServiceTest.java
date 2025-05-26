package com.example.tokenapp.user;

import com.example.tokenapp.exception.UserAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserEntityRepository userEntityRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void givenUsername_whenVerifyUsername_thenReturnResponse() {
        when(userEntityRepository.findByUsername("daniel")).thenReturn(Optional.of(new UserEntity()));
        when(userEntityRepository.findByUsername("impostor")).thenReturn(Optional.of(new UserEntity()));

        // TODO

        verify(userEntityRepository).save(any(UserEntity.class));

    }




    @Test
    void givenExistingUsername_whenRegisterUser_thenThrowUserAlreadyRegisteredException() {
        when(userEntityRepository.findByUsername("TestUser1")).thenReturn(Optional.of(new UserEntity()));
        Assertions.assertThrows(
                UserAlreadyRegisteredException.class,
                () -> userService.registerUser("TestUser1")
        );
    }

    @Test
    void givenNewUsername_whenRegisterUser_thenReturnSavedUser() {
        when(userEntityRepository.findByUsername("impostor")).thenReturn(Optional.empty());
        UserEntity newUser = userService.registerUser("impostor");
        verify(userEntityRepository).save(any(UserEntity.class));
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals("impostor", newUser.getUsername());
    }

}