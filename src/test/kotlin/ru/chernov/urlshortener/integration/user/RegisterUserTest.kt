package ru.chernov.urlshortener.integration.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.chernov.urlshortener.AbstractTest
import ru.chernov.urlshortener.controller.UserController
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.repository.user.UserRepository

class RegisterUserTest : AbstractTest() {

    @Autowired
    private lateinit var userController: UserController

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun shouldRegisterUser() {
        userController.register(UserRegisterRequest("new_username", "12345"))

        assertThat(userRepository.existsByUsername("new_username")).isTrue
    }

}