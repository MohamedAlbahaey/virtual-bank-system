package com.example.userService.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.userService.dtos.UserLogin;
import com.example.userService.dtos.LoginResponse;
import com.example.userService.dtos.UserProfile;
import com.example.userService.dtos.UserRegistration;
import com.example.userService.dtos.UserResponse;
import com.example.userService.model.Users;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserProfile toUserProfile(Users user);

    UserResponse toRegisterResponse(Users user);

    LoginResponse toLoginResponse(Users user);

    Users toUser(UserRegistration user);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Users toUser(UserLogin user);

}
