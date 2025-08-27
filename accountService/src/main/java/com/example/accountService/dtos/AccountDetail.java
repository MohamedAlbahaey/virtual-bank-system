package com.example.accountService.dtos;

import java.util.UUID;

import com.example.accountService.enums.AccountStatus;
import com.example.accountService.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDetail {
    @JsonProperty("accountId")
    private UUID id;

    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private AccountStatus status;
}
