package ru.chernov.urlshortener.integration.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.chernov.urlshortener.AbstractTest
import ru.chernov.urlshortener.dto.user.UserRegisterRequest

class RegisterUserTest : AbstractTest() {

    @Test
    fun shouldRegisterUser() {
        // TODO(mock mvc helper)
        userController.register(UserRegisterRequest("new_username", "12345"))

        assertThat(userRepository.existsByUsername("new_username")).isTrue
    }

}