package ru.chernov.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenerApplication

fun main() {
    runApplication<UrlShortenerApplication>()
}