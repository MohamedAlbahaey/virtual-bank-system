package com.example.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userService.model.Users;
import com.example.userService.repositery.UserRepositery;

@Service
public class UserService {

    @Autowired
    UserRepositery userRepo;

    public String registerUser(Users user){
        userRepo.save(user);
        return "{ " +
                    "userId :" + user.getId() +
                    "username :" + user.getUsername() +
                    "message : Successful" +
                  "} ";

        // try{
            
        //     Users registeredUser = userRepo.findById(userRepo.findAll().size());
        //    return "{ " +
        //             "username :" + registeredUser.getUsername() +
        //             "userId :" + registeredUser.getId() +
        //             "message : Successful" +
        //           "} ";
        // }
        // catch (Exception e){
        //     return e.getMessage();
        // }
    }

    // public UserResponse loginUser(String username, String password) {
    //     return userRepo.findByUsernameAndPassword(username, password);
    // }
    
    public Users getUserProfile(int userId){
        return userRepo.findById(userId).orElse(null);
    }

}
