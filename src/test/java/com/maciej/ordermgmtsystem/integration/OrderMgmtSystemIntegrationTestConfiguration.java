package com.maciej.ordermgmtsystem.integration;

import com.maciej.ordermgmtsystem.MockLoginHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMgmtSystemIntegrationTestConfiguration {


    @Bean
    public MockLoginHelper mockLoginHelper() {
        return new MockLoginHelper();
    }
}
