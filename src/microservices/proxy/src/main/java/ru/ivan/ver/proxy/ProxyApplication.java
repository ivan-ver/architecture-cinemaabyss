package ru.ivan.ver.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.ivan.ver.proxy.configs.FeatureFlagsProperties;

@SpringBootApplication
@EnableConfigurationProperties(FeatureFlagsProperties.class)
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
