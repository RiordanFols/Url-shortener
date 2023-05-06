package ru.chernov.urlshortener.dto.user

import jakarta.validation.constraints.NotNull
import ru.chernov.urlshortener.validation.constraint.AvailableUsername


data class UserRegisterRequest(
        @AvailableUsername
        @NotNull
        var username: String?,
        @NotNull
        var password: String?
)