package com.maciej.ordermgmtsystem.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maciej.ordermgmtsystem.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

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
        ResultActions resultActions = mockMvc.perform(get("/rest/products"));
        MvcResult mvcResult = resultActions.andExpect(status().is2xxSuccessful()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<Product> result = mapper.readValue(contentAsString, new TypeReference<List<Product>>() { });
        assertFalse("received no products", result.isEmpty());
        // I think assertions at this point are strong enough for this integration test as list of products is fixed
        // for demonstration purposes. This could be expanded and products injected at the beginning of the test.
    }
}
