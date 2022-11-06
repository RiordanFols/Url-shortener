package ru.chernov.urlshortener.entity.id

import ru.chernov.urlshortener.annotation.Lombok
import ru.chernov.urlshortener.entity.User
import java.io.Serializable

@Lombok
class UserRoleId : Serializable {
    private var userId: User? = null
    private var role: String? = null
}