package ru.chernov.urlshortener.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "application.redis")
public class RedisProperties {
    private String url;
    private String keyPrefix;

}
