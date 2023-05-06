package ru.chernov.urlshortener.dto.user

import jakarta.validation.constraints.NotNull
import ru.chernov.urlshortener.validation.constraint.AvailableUsername


class UserRegisterRequest(
        @AvailableUsername
        @NotNull
        var username: String?,
        @NotNull
        var password: String?
)