package ru.chernov.urlshortener.dto.user

import java.util.UUID

data class UserResponse(
        var id: Long,
        var username: String,
        var token: UUID
)