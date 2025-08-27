package com.example.accountService.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountResponse {
    @JsonProperty("accountId")
    private UUID id;
    private String accountNumber;
    private String message;
}
