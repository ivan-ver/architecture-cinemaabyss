package ru.ivan.ver.events.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.ivan.ver.events.dto.EventType;
import ru.ivan.ver.events.dto.ResponseStatus;

import java.time.LocalDateTime;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResponse<T> {
    String status;
    Integer partition;
    Long offset;
    Event<T> event;

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Event<T> {
        String id;
        EventType event;
        LocalDateTime timestamp;
        T payload;
    }
}
