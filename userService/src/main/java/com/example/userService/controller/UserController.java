package com.example.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.model.Users;
import com.example.userService.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    
    @PostMapping("/register")
    public String registerUser(@RequestBody Users user){
        return userService.registerUser(user);
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){

    //     UserResponse user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

    //     return authenticated ? "Authenticated" : "Invalid credentials";
    // }
    
    @GetMapping("/{userId}/profile")
    public Users getUserProfile(@PathVariable int userId){
        return userService.getUserProfile(userId);
    }

}
