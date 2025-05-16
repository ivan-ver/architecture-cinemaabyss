package ru.ivan.ver.events.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.ivan.ver.events.dto.MovieDto;
import ru.ivan.ver.events.dto.PaymentDto;
import ru.ivan.ver.events.dto.UserDto;

import static ru.ivan.ver.events.config.StaticData.MOVIE_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.PAYMENT_EVENT_TOPIC;
import static ru.ivan.ver.events.config.StaticData.USER_EVENT_TOPIC;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {
    @KafkaListener(topics = MOVIE_EVENT_TOPIC)
    public void getMovieEvent(MovieDto dto,
                              @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("Key: {}; Movie event: {}", key, dto);
    }

    @KafkaListener(topics = PAYMENT_EVENT_TOPIC)
    public void getPaymentEvent(PaymentDto dto,
                                @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("Key: {}; Payment event: {}", key, dto);
    }

    @KafkaListener(topics = USER_EVENT_TOPIC)
    public void getUserEvent(UserDto dto,
                             @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("Key: {}; User event: {}", key, dto);
    }
}
