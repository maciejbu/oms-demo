package com.maciej.ordermgmtsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maciej.ordermgmtsystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockLoginHelper {
    private static final UserDto TEST_USER = new UserDto();
    static {
        TEST_USER.setUsername("testusername");
        TEST_USER.setPassword("testpassword");
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsManager userManager;

    public void loginAsTestUser() {
        try {
            mockMvc.perform(post("/rest/users").content(toJson(TEST_USER)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
            mockMvc.perform(post("/rest/login").content(toJson(TEST_USER)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toJson(Object userDetails) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(userDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
