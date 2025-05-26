package com.example.tokenapp.authorize;

import com.example.tokenapp.exception.InvalidTokenException;
import com.example.tokenapp.exception.UserNotFoundException;
import com.example.tokenapp.user.UserEntity;
import com.example.tokenapp.user.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class AuthorizeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @BeforeEach
    void setUp() {
        userEntityRepository.deleteAll();
        UserEntity user = new UserEntity();
        user.setUsername("testUser123");
        user.setToken("5bc68ba5-c43d-4409-8f68-9a91d8872e55");
        userEntityRepository.save(user);
    }

    @Test
    void givenCorrectData_whenVerifyToken_thenReturnStatusOK() throws Exception {
        mockMvc.perform(post("/auth/verifyToken")
                        .content("{\"username\":\"testUser123\",\"token\":\"5bc68ba5-c43d-4409-8f68-9a91d8872e55\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser123"))
                .andExpect(jsonPath("$.token").value("5bc68ba5-c43d-4409-8f68-9a91d8872e55"));
    }

    @Test
    void givenIncorrectUsername_whenVerifyToken_thenReturnUserNotFoundExceptionAndStatusNotFound() throws Exception {
        mockMvc.perform(post("/auth/verifyToken")
                        .content("{\"username\":\"incorrectUser123\",\"token\":\"5bc68ba5-c43d-4409-8f68-9a91d8872e55\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.message").value("incorrectUser123"));
    }

    @Test
    void givenCorrectUsernameIncorrectToken_whenVerifyToken_thenReturnInvalidTokenExceptionAndStatusForbidden() throws Exception {
        mockMvc.perform(post("/auth/verifyToken")
                        .content("{\"username\":\"testUser123\",\"token\":\"5bc68ba5-c43d-4409-8f68-9a91d8872e54\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(result -> assertInstanceOf(InvalidTokenException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.message").value("Invalid token: 5bc68ba5-c43d-4409-8f68-9a91d8872e54"));
    }
}