package ru.ivan.ver.events.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.ivan.ver.events.dto.request.MovieDtoRequest;
import ru.ivan.ver.events.dto.request.PaymentDtoRequest;
import ru.ivan.ver.events.dto.request.UserDtoRequest;
import ru.ivan.ver.events.dto.response.EventResponse;

import java.time.LocalDateTime;
import java.util.UUID;

import static ru.ivan.ver.events.config.StaticData.MOVIE_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.PAYMENT_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.USER_EVENT_TOPIC;
import static ru.ivan.ver.events.dto.EventType.MOVIE;
import static ru.ivan.ver.events.dto.ResponseStatus.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProducerService<T> {
    KafkaTemplate<String, T> kafkaTemplate;

    @SneakyThrows
    public EventResponse<T> sendEvent(T dto, Long id) {
        String topic;
        if (dto instanceof MovieDtoRequest) {
            topic = MOVIE_EVENT_TOPIC;
        } else if (dto instanceof PaymentDtoRequest) {
            topic = PAYMENT_EVENT_TOPIC;
        } else if (dto instanceof UserDtoRequest) {
            topic = USER_EVENT_TOPIC;
        } else if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        } else {
            throw new IllegalArgumentException("Unsupported DTO type: " + dto.getClass().getName());
        }
        SendResult<String, T> result =
                kafkaTemplate.send(topic, UUID.randomUUID().toString(), dto).get();

        return EventResponse.<T>builder()
                .status(SUCCESS.getValue())
                .offset(result.getRecordMetadata().offset())
                .partition(result.getRecordMetadata().partition())
                .event(EventResponse.Event.<T>builder()
                        .id("%s-%s-viewed".formatted(MOVIE.getValue(), id))
                        .event(MOVIE)
                        .timestamp(LocalDateTime.now())
                        .payload(dto)
                        .build())
                .build();
    }
}
