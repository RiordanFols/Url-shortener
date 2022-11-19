package ru.chernov.urlshortener.mapper

import org.springframework.stereotype.Component
import ru.chernov.urlshortener.dto.user.UserResponse
import ru.chernov.urlshortener.entity.user.User

// TODO: add mapstruct
@Component
class UserMapper {

    fun toResponse(user: User): UserResponse {
        return UserResponse(user.id, user.username, user.token)
    }

}