package ru.chernov.urlshortener.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.chernov.urlshortener.consts.PATH_API_USERS
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.service.user.UserService

@RestController
class UserController(private val userService: UserService) {

    // TODO: preauthorize Role GUEST
    @GetMapping(PATH_API_USERS)
    fun register(@Valid registerRequest: UserRegisterRequest) {
        userService.register(registerRequest)
    }

}