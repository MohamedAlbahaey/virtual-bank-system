package com.example.userService.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userService.model.Users;

@Repository
public interface UserRepositery extends JpaRepository<Users, Integer>{
    
    Users findByUsernameAndPassword(String username, String password);
}
