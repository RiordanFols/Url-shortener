package ru.chernov.urlshortener.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {
    private String secretKey;
    private Long expirationMinutes;

}
