package ru.chernov.urlshortener

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import ru.chernov.urlshortener.controller.UserController
import ru.chernov.urlshortener.repository.UserRepository


abstract class TestDependencies {

    @Autowired
    protected lateinit var context: WebApplicationContext

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var userController: UserController

    @Autowired
    protected lateinit var userRepository: UserRepository

}