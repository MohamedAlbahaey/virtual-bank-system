package com.example.transactionService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionStatus {
    INITIATED,
    SUCCESS,
    FAILED;

    @JsonCreator
    public static TransactionStatus fromString(String value) {
        return value == null ? null : TransactionStatus.valueOf(value.toUpperCase());
    }
}
