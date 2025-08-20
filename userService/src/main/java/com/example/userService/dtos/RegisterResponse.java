package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegisterResponse {
    @JsonProperty("userId")
    private UUID id;
    private String username;
    private String message;
}
