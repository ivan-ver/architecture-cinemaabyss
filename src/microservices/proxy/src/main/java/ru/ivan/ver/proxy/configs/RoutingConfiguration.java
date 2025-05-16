package ru.ivan.ver.proxy.configs;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoutingConfiguration {
    @Value("${url.monolith}")
    String monolithUrl;
    @Value("${url.movies-service}")
    String moviesServiceUrl;
    @Value("${url.events-service}")
    String eventsServiceUrl;
    @Value("${feature-flags.gradual-migration}")
    boolean gradualMigration;
    @Value("${feature-flags.movies-migration-percent}")
    int moviesMigrationPercent;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes()
                .route(p -> p
                        .path("/api/proxy/health")
                        .filters(f -> f.setPath("/api/proxy/health")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, originalResponse) -> Mono.just("{\"status\": \"true\"}")))
                        .uri("no://op"))
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
                        .uri(eventsServiceUrl));

        if (gradualMigration) {
            routes.route("movies-to-microservice", p -> p
                            .path("/api/movies/**")
                            .and()
                            .weight("movies-group", moviesMigrationPercent)
                            .uri(moviesServiceUrl))
                    .route("movies-to-monolith", p -> p
                            .path("/api/movies/**")
                            .and()
                            .weight("movies-group", 100 - moviesMigrationPercent)
                            .uri(monolithUrl));
        } else {
            routes.route(p -> p
                    .path("/api/movies/**")
                    .uri(monolithUrl));
        }

        return routes.build();
    }
}
