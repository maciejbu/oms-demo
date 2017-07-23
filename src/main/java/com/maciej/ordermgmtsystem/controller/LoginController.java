package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = "/rest/login", produces={"application/json"})
    public ResponseEntity login(@RequestBody UserDto user) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
