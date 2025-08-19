package com.example.userService.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.dtos.LoginRequest;
import com.example.userService.dtos.LoginResponse;
import com.example.userService.dtos.RegisterRequest;
import com.example.userService.dtos.RegisterResponse;
import com.example.userService.dtos.UserDto;
import com.example.userService.mappers.UserMapper;
import com.example.userService.model.Users;
import com.example.userService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest user) {
        Users registeredUser = userService.registerUser(userMapper.toEntity(user));

        if (registeredUser == null) {
            return ResponseEntity.status(409).build();
        }

        RegisterResponse response = userMapper.toRegisterResponse(registeredUser);

        response.setMessage("User Created");

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Users user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(userMapper.toLoginResponse(user));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable UUID userId) {
        Users user = userService.getUserProfile(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/")
    public String homePage() {
        return "User Service";
    }

}
