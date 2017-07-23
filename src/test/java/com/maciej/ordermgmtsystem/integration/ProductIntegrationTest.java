package com.maciej.ordermgmtsystem.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserDetailsManager userManager;

    @Test
    public void accessDeniedWithoutLogin() throws Exception {
        mockMvc.perform(get("/rest/products")).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void listProducts() throws Exception {
        mockMvc.perform(get("/rest/products")).andExpect(status().is2xxSuccessful());
    }
}
