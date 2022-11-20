package ru.chernov.urlshortener.dto.link

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class LinkShortenRequest(
        @NotBlank
        @Length(max = 1000)
        val link: String
)