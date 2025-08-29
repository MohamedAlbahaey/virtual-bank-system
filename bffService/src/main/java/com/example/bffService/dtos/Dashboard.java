package com.example.bffService.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Dashboard {
    private UUID userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<AccountDetail> accounts;
}
