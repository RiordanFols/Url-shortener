package ru.chernov.urlshortener.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "application.link")
public class LinkProperties {
    private Integer codeLength;
    private String prefix;

}
