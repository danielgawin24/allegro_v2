package com.example.tokenapp.user;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

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
    void givenNewUsernameRequest_whenRegister_thenReturnStatusCREATED() throws Exception {
        mockMvc.perform(post("/user/register")
                        .content("{\"username\":\"newUser123\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void givenExistingUsernameRequest_whenRegister_thenReturnStatusCONFLICT() throws Exception {
        mockMvc.perform(post("/user/register")
                        .content("{\"username\":\"testUser123\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void givenCorrectUsername_whenGetUser_thenReturnStatusOK() throws Exception {
        mockMvc.perform(get("/user/{username}", "testUser123")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser123"));
    }

    @Test
    void givenIncorrectUsername_whenGetUser_thenReturnStatusNOTFOUND() throws Exception {
        mockMvc.perform(get("/user/{username}", "admin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}