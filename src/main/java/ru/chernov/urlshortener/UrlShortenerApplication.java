package ru.chernov.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.chernov.urlshortener.config.properties.LinkProperties;
import ru.chernov.urlshortener.config.properties.RedisProperties;


@EnableConfigurationProperties({
        LinkProperties.class,
        RedisProperties.class
})
@EnableJpaRepositories
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

}