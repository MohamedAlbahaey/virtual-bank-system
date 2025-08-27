package com.example.transactionService.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.transactionService.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransferResponse {
    @JsonProperty("transactionId")
    private UUID id;
    private TransactionStatus status;
    private LocalDateTime timestamp;
}
