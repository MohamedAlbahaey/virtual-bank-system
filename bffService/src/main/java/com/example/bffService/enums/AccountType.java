package com.example.bffService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountType {
    SAVINGS,
    CHECKING;

    @JsonCreator
    public static AccountType fromString(String value) {
        return value == null ? null : AccountType.valueOf(value.toUpperCase());
    }
}