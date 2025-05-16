package ru.ivan.ver.events.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.ver.events.dto.PaymentDto;
import ru.ivan.ver.events.service.ProducerService;

import static ru.ivan.ver.events.config.StaticData.MAIN_URL;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(MAIN_URL)
public class PaymentController {
    ProducerService<PaymentDto> service;

    @PostMapping(value = "/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createMessage(@RequestBody PaymentDto dto) {
        service.sendEvent(dto);
    }
}
