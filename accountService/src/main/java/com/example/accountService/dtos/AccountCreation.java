package com.example.accountService.dtos;

import java.util.UUID;

import com.example.accountService.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountCreation {
    private UUID userId;
    private AccountType accountType;
    @JsonProperty("initialBalance")
    private double balance;
}
