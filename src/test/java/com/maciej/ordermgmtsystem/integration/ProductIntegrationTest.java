package com.maciej.ordermgmtsystem.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maciej.ordermgmtsystem.MockLoginHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    private static final UserDetails TEST_USER = new User("testusername", "testpassword", Collections.emptyList());

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserDetailsManager userManager;

    @Autowired
    MockLoginHelper mockLoginHelper;

    @Test
    public void accessDeniedWithoutLogin() throws Exception {
        mockMvc.perform(get("/rest/products")).andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser
    public void listProducts() throws Exception {
       // mockLoginHelper.loginAsTestUser();
        mockMvc.perform(get("/rest/products")).andExpect(status().is2xxSuccessful());
    }

    public void loginTestUser() {
        try {
            userManager.createUser(TEST_USER);
            mockMvc.perform(post("/rest/login").content(toJson(TEST_USER)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toJson(UserDetails userDetails) {
       ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(userDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
