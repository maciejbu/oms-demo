package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
public class RegistrationController {
    // Production code would have proper password (likely hashcode, salt) management with roles
    private static final Collection<GrantedAuthority> AUTHORITIES = Collections.emptyList();
    private final UserDetailsManager userManager;

    public RegistrationController(UserDetailsManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping(path = "/rest/users", produces={"application/json"})
    public ResponseEntity create( @RequestBody UserDto user) {
        Assert.hasText(user.getUsername(), "Username must not be empty");
        Assert.hasText(user.getPassword(), "Password must not be empty");

        userManager.createUser(new User(user.getUsername(), user.getPassword(), AUTHORITIES));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
