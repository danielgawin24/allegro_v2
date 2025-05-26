package com.example.tokenapp.token;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.profiles.active=test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @BeforeEach
    void setUp() {
        userEntityRepository.deleteAll();
        UserEntity user = new UserEntity();
        user.setUsername("testUser123");
        userEntityRepository.save(user);
    }

    @Test
    void givenExistingUsername_whenVerifyUsername_thenReturnStatusOK() throws Exception {
        mockMvc.perform(post("/token/generate")
                        .content("{\"username\":\"testUser123\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser123"));
    }

    @Test
    void givenNewUsername_whenVerifyUsername_thenReturnStatusOK() throws Exception {
        mockMvc.perform(post("/token/generate")
                        .content("{\"username\":\"newUser123\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUser123"));
    }
}