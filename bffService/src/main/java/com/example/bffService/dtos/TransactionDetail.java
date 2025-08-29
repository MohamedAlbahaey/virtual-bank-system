package com.example.bffService.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionDetail {
    @JsonProperty("transactionId")
    private UUID id;

    private UUID accountId;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
}
