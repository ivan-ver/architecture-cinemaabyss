package ru.ivan.ver.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    MOVIE("movie"),
    PAYMENT("payment"),
    USER("user");
    private final String value;
}
