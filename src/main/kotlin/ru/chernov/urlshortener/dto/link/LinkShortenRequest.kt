package ru.chernov.urlshortener.dto.link

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class LinkShortenRequest(
        @NotBlank
        @field:Size(max = 1000, message = "Link is too big")
        val link: String
)