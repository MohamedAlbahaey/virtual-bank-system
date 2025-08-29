package com.example.bffService.dtos;

import java.util.List;
import java.util.UUID;

import com.example.bffService.enums.AccountStatus;
import com.example.bffService.enums.AccountType;
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
    private List<TransactionDetail> transactions;
}
