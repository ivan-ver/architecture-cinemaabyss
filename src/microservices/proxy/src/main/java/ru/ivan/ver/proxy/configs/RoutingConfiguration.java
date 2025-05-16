package ru.ivan.ver.proxy.configs;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ivan.ver.proxy.service.GradualMigrationRoutePredicateFactory;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoutingConfiguration {
    @Value("${url.monolith}")
    String monolithUrl;
    @Value("${url.movies-service}")
    String moviesServiceUrl;
    @Value("${url.events-service}")
    String eventsServiceUrl;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/health/**")
                        .uri(monolithUrl))
                .route("movies-to-microservice", p -> p
                        .path("/api/movies/**")
                        .and()
                        .predicate(new GradualMigrationRoutePredicateFactory()
                                .apply(new GradualMigrationRoutePredicateFactory.Config()))
                        .uri(moviesServiceUrl))
                .route("movies-to-monolith", p -> p
                        .path("/api/movies/**")
                        .uri(monolithUrl))
                .route(p -> p
                        .path("/api/users/**")
                        .uri(monolithUrl))
                .route(p -> p
                        .path("/api/payments/**")
                        .uri(monolithUrl))
                .route(p -> p
                        .path("/api/subscriptions/**")
                        .uri(monolithUrl))
                .route(p -> p
                        .path("/api/events/**")
                        .uri(eventsServiceUrl))

                .build();
    }
}
