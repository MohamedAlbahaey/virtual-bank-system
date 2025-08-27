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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.userService.dtos.UserLogin;
import com.example.userService.dtos.LoginResponse;
import com.example.userService.dtos.UserProfile;
import com.example.userService.dtos.UserRegistration;
import com.example.userService.dtos.UserResponse;
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
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegistration registerUser,
            UriComponentsBuilder uriBuilder) {

        Users user = userService.registerUser(userMapper.toUser(registerUser));

        var uri = uriBuilder.path("/users/{userId}/profile").buildAndExpand(user.getId()).toUri();

        UserResponse response = userMapper.toRegisterResponse(user);
        response.setMessage("User registered successfully.");

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserLogin loginRequest) {

        Users user = userService.loginUser(userMapper.toUser(loginRequest));

        return ResponseEntity.ok(userMapper.toLoginResponse(user));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable UUID userId) {

        Users user = userService.getUserProfile(userId);

        return ResponseEntity.ok(userMapper.toUserProfile(user));
    }

    @GetMapping("/")
    public String homePage() {
        return "User Service";
    }

}
