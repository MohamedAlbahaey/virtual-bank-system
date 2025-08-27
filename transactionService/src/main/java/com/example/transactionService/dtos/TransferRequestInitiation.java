package com.example.transactionService.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class TransferRequestInitiation {
    private UUID fromAccountId;
    private UUID toAccountId;
    private double amount;
    private String description;
}
