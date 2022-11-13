package ru.chernov.urlshortener.entity.id

import ru.chernov.urlshortener.entity.user.User
import java.io.Serializable


data class UserRoleId(
        var user: User,
        var role: String
) : Serializable