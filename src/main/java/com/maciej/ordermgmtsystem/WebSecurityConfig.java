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
                .antMatchers("/", "/home", "/register", "/login").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/rest/users").permitAll()
                .antMatchers("/rest/login").permitAll()
                .anyRequest().authenticated();
              //  .and().formLogin().loginPage("/login").permitAll();

        //http.sessionManagement().sessionAuthenticationStrategy(strategy);


        // CSRF prevents using REST with independent requests - disabling
        http.csrf().ignoringAntMatchers("/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager());
        //auth.authenticationProvider(provider);
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }
}
