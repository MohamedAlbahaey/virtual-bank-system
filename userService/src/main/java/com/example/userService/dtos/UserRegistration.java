package com.example.userService.dtos;

import lombok.Data;

@Data
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
