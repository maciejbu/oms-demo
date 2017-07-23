package com.maciej.ordermgmtsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // HTML pages
                .antMatchers("/", "/home", "/register", "/login").permitAll()
                .antMatchers("/js/**").permitAll()
                // user to freely access registration and login, hence permitting all
                .antMatchers("/rest/users").permitAll()
                .antMatchers("/rest/login").permitAll()
                .anyRequest().authenticated();

        // CSRF prevents using REST with independent requests; this could be handled differently depending on requirements
        // https://github.com/Zuehlke/springboot-sec-tutor/tree/rest-auth
        http.csrf().ignoringAntMatchers("/rest/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager());
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        // Relying purely on in-memory manager for this demo
        return new InMemoryUserDetailsManager();
    }
}
