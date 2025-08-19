package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {
    @JsonProperty("userId")
    private UUID id;
    private String username;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
