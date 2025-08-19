package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    @JsonProperty("userId")
    private UUID id;
    private String username;
}
