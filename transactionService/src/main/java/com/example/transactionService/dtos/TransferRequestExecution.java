package com.example.transactionService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransferRequestExecution {
    @JsonProperty("transactionId")
    private UUID id;
}
