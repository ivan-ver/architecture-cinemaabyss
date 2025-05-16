package ru.ivan.ver.events.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static ru.ivan.ver.events.config.StaticData.MAIN_URL;

@Configuration
public class ApiConfig {

    @Bean
    public RouterFunction<ServerResponse> routeHealth() {
        return route(GET(MAIN_URL + "/health"), request -> ServerResponse.ok().body(Map.of("status", true)));
    }
}
