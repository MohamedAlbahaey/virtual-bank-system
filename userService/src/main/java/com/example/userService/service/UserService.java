package com.example.userService.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userService.model.Users;
import com.example.userService.repo.UserRepositery;

@Service
public class UserService {

    @Autowired
    UserRepositery userRepo;

    public Users registerUser(Users user) {
        if (userRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
            return userRepo.save(user);
        }
        return null;
    }

    public Users loginUser(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    public Users getUserProfile(UUID userId) {
        return userRepo.findById(userId).orElse(null);
    }

}
