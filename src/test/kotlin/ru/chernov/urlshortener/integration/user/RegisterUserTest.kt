package ru.chernov.urlshortener.integration.user

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.chernov.urlshortener.AbstractTest
import ru.chernov.urlshortener.consts.rest.PATH_API_USERS
import ru.chernov.urlshortener.dto.user.UserRegisterRequest
import ru.chernov.urlshortener.helper.postJson

private const val NEW_TEST_USERNAME = "new_username"


class RegisterUserTest : AbstractTest() {
    private val authentication: UserRequestPostProcessor = user("test").password("password")

    @Test
    fun shouldRegisterUser() {
        val userRegisterRequest = UserRegisterRequest(NEW_TEST_USERNAME, "12345")
        mockMvc.perform(postJson(PATH_API_USERS)
                .with(authentication)
                .content(content(userRegisterRequest)))
                .andExpect(status().isOk)

        assertTrue(userRepository.existsByUsername(NEW_TEST_USERNAME))
    }


    @Test
    fun shouldThrowCauseUsernameExist() {
        val userRegisterRequest = UserRegisterRequest(NEW_TEST_USERNAME, "12345")
        mockMvc.perform(postJson(PATH_API_USERS)
                .with(authentication)
                .content(content(userRegisterRequest)))
                .andExpect(status().isOk)

        mockMvc.perform(postJson(PATH_API_USERS)
                .with(authentication)
                .content(content(userRegisterRequest)))
                .andExpect(status().isBadRequest)

    }


    @AfterEach
    fun afterEach() {
        if (userRepository.existsByUsername(NEW_TEST_USERNAME)) {
            userRepository.deleteByUsername(NEW_TEST_USERNAME)
        }
    }

}