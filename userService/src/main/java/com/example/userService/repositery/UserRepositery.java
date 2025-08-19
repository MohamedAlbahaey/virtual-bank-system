package com.example.userService.repositery;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userService.model.Users;

@Repository
public interface UserRepositery extends JpaRepository<Users, UUID> {

    Users findByUsernameAndPassword(String username, String password);

    Users findByUsernameOrEmail(String username, String email);
}
