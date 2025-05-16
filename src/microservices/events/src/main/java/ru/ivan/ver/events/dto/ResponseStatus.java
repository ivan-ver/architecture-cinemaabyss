package ru.ivan.ver.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("success"),
    FAILED("failed");
    private final String value;
}
