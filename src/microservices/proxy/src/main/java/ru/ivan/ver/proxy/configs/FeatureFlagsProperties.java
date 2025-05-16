package ru.ivan.ver.proxy.configs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "feature-flags")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeatureFlagsProperties {
    boolean gradualMigration;
    int moviesMigrationPercent;
}
