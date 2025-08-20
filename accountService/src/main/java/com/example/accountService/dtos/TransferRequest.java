package com.example.accountService.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class TransferRequest {
    private UUID fromAccountId;
    private UUID toAccountId;
    private double amount;
}
