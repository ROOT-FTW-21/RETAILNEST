package com.retailnest.auth_service.dto;

public class UserLoginDto {
    private String identifier, password;

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
