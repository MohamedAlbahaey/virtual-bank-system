package com.example.loggingService.enums;

public enum MessageType {
    Request,
    Response;

    // @JsonCreator
    // public static MessageType fromString(String value) {
    // return value == null ? null : MessageType.valueOf(value.toUpperCase());
    // }
}
