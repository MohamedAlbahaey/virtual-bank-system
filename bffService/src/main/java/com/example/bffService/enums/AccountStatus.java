package com.example.bffService.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountStatus {
    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static AccountStatus fromString(String value) {
        return value == null ? null : AccountStatus.valueOf(value.toUpperCase());
    }
}
