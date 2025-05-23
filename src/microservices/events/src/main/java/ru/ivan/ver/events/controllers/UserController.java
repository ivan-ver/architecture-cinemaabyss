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
import ru.ivan.ver.events.dto.request.UserDtoRequest;
import ru.ivan.ver.events.dto.response.EventResponse;
import ru.ivan.ver.events.service.ProducerService;

import static ru.ivan.ver.events.config.StaticData.MAIN_URL;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(MAIN_URL)
public class UserController {
    ProducerService<UserDtoRequest> service;

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse<UserDtoRequest> createMessage(@RequestBody UserDtoRequest dto) {
        return service.sendEvent(dto, dto.getUserId());
    }
}
