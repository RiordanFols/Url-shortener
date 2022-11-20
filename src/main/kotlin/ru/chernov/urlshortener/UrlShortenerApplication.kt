package ru.chernov.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.chernov.urlshortener.config.properties.LinkProperties
import ru.chernov.urlshortener.config.properties.RedisProperties

@EnableConfigurationProperties(LinkProperties::class, RedisProperties::class)
@SpringBootApplication
class UrlShortenerApplication

fun main() {
    runApplication<UrlShortenerApplication>()
}