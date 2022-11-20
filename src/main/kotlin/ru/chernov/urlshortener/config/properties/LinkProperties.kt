package ru.chernov.urlshortener.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "application.link")
data class LinkProperties(
        val codeLength: Int,
        val prefix: String
)