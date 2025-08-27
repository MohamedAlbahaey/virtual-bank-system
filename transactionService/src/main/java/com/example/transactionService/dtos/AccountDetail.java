package com.example.transactionService.dtos;

import java.util.UUID;

import com.example.transactionService.enums.AccountStatus;

import lombok.Data;

@Data
public class AccountDetail {
    private UUID accountId;
    private double balance;
    private AccountStatus status;
}
