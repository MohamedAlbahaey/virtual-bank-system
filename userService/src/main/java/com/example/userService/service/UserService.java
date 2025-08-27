package com.example.userService.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userService.exception.ConflictRequestException;
import com.example.userService.exception.NotFoundException;
import com.example.userService.exception.UnauthorizedException;
import com.example.userService.model.Users;
import com.example.userService.repo.UserRepositery;

@Service
public class UserService {

    @Autowired
    UserRepositery userRepo;

    public Users registerUser(Users user) {
        if (userRepo.findByUsername(user.getUsername()) != null || userRepo.findByEmail(user.getEmail()) != null) {
            throw new ConflictRequestException("Username or email already exists.");
        }

        return userRepo.save(user);
    }

    public Users loginUser(Users user) {
        Users response = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (response == null)
            throw new UnauthorizedException("Invalid username or password.");

        return response;
    }

    public Users getUserProfile(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " not found."));
    }

}
