package com.example.userService.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userService.model.Users;

@Repository
public interface UserRepositery extends JpaRepository<Users, UUID> {

    Users findByUsername(String username);

    Users findByEmail(String email);

    Users findByUsernameAndPassword(String username, String password);
}
