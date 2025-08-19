package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("userId")
    private UUID id;
    private String username;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
