package ru.chernov.urlshortener.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "application.redis")
data class RedisProperties(
        val url: String,
        val keyPrefix: String
)