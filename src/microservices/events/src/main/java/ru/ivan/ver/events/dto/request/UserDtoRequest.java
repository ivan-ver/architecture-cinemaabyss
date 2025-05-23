package ru.ivan.ver.events.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDtoRequest {
    @JsonProperty("user_id")
    Long userId;
    String action;
    LocalDateTime timestamp;
    String username;
    String email;
}
