package ru.chernov.urlshortener.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.chernov.urlshortener.consts.rest.PATH_API_USERS
import ru.chernov.urlshortener.consts.rest.PATH_API_USERS_ID
import ru.chernov.urlshortener.consts.rest.USER_ID
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.dto.user.UserResponse
import ru.chernov.urlshortener.mapper.UserMapper
import ru.chernov.urlshortener.service.UserService

@RestController
class UserController(
        private val userService: UserService,
        private val userMapper: UserMapper
) {

    @GetMapping(PATH_API_USERS_ID)
    fun get(@PathVariable(USER_ID) userId: Long): UserResponse {
        val user = userService.findById(userId)
        return userMapper.toResponse(user)
    }

    // TODO(preauthorize Role GUEST)
    @PostMapping(PATH_API_USERS)
    fun register(@Valid registerRequest: UserRegisterRequest) {
        userService.register(registerRequest)
    }

    //TODO(change password endpoint)

    // TODO(freeze user endpoint)

    // TODO(recover user endpoint)

}