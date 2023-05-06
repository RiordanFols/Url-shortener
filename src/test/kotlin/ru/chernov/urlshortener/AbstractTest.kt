package ru.chernov.urlshortener

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
abstract class AbstractTest : TestDependencies() {

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .addFilter<DefaultMockMvcBuilder>({ request: ServletRequest, response: ServletResponse, chain: FilterChain ->
                    request.characterEncoding = "UTF-8"
                    response.characterEncoding = "UTF-8"
                    chain.doFilter(request, response)
                })
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
                .build()
    }


    fun content(any: Any): ByteArray {
        return objectMapper.writeValueAsBytes(any)
    }

}


