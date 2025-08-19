package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterResponse {
    @JsonProperty("userId")
    private UUID id;
    private String username;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
