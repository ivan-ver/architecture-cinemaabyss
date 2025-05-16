package ru.ivan.ver.proxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

@Component
public class GradualMigrationRoutePredicateFactory
        extends AbstractRoutePredicateFactory<GradualMigrationRoutePredicateFactory.Config> {


    @Value("${feature-flags.gradual-migration}")
    boolean gradualMigration;
    @Value("${feature-flags.movies-migration-percent}")
    int moviesMigrationPercent;

    public GradualMigrationRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> gradualMigration && ThreadLocalRandom.current().nextInt(100) <= moviesMigrationPercent;
    }

    public static class Config {
    }
}
