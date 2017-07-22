package com.maciej.ordermgmtsystem.domain;

public class User {
    private final String username;

    // Separate object in a real system
    private final String company;
    private final String password;


    public User(String username, String password, String company) {
        this.username = username;
        this.company = company;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
