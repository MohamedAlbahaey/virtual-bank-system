package com.example.userService.mappers;

import org.mapstruct.Mapper;

import com.example.userService.dtos.LoginRequest;
import com.example.userService.dtos.LoginResponse;
import com.example.userService.dtos.RegisterRequest;
import com.example.userService.dtos.RegisterResponse;
import com.example.userService.dtos.UserDto;
import com.example.userService.model.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);

    RegisterResponse toRegisterResponse(Users user);

    LoginResponse toLoginResponse(Users user);

    Users toEntity(RegisterRequest user);

    Users toEntity(LoginRequest user);

}
