package com.example.userService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {

    @JsonProperty("userId")
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
