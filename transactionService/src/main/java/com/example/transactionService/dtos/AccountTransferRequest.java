package com.example.transactionService.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AccountTransferRequest {
    private UUID fromAccountId;
    private UUID toAccountId;
    private double amount;
}
