package com.example.userService.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.userService.dtos.LoginRequest;
import com.example.userService.dtos.LoginResponse;
import com.example.userService.dtos.RegisterRequest;
import com.example.userService.dtos.RegisterResponse;
import com.example.userService.dtos.UserDto;
import com.example.userService.model.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);

    @Mapping(target = "message", ignore = true)
    RegisterResponse toRegisterResponse(Users user);

    LoginResponse toLoginResponse(Users user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Users toEntity(RegisterRequest user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDataeTime.now())")
    Users toEntity(LoginRequest user);

}
