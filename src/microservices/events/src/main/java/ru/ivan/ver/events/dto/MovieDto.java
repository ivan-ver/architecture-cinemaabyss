package ru.ivan.ver.events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {
    @JsonProperty("movie_id")
    Long movieId;
    String title;
    String action;
    @JsonProperty("user_id")
    Long userId;
    Double rating;
    List<String> genres;
    String description;
}
