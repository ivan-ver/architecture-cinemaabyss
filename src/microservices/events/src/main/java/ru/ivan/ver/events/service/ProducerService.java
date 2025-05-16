package ru.ivan.ver.events.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.ivan.ver.events.dto.MovieDto;
import ru.ivan.ver.events.dto.PaymentDto;
import ru.ivan.ver.events.dto.UserDto;

import java.util.UUID;

import static ru.ivan.ver.events.config.StaticData.MOVIE_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.PAYMENT_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.USER_EVENT_TOPIC;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProducerService<T> {
    KafkaTemplate<String, T> kafkaTemplate;

    public void sendEvent(T dto) {
        String topic = switch (dto) {
            case MovieDto it -> MOVIE_EVENT_TOPIC;
            case PaymentDto it -> PAYMENT_EVENT_TOPIC;
            case UserDto it -> USER_EVENT_TOPIC;
            default -> throw new RuntimeException("No such DTO");
        };
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), dto);
    }
}
