package ru.chernov.urlshortener

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.chernov.urlshortener.controller.UserController
import ru.chernov.urlshortener.repository.UserRepository

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
abstract class AbstractTest {

    @Autowired
    protected lateinit var userController: UserController

    @Autowired
    protected lateinit var userRepository: UserRepository
}
