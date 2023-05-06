package ru.chernov.urlshortener

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.reflect.KClass


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = ["/sql/clear.sql"], executionPhase = BEFORE_TEST_METHOD)
abstract class AbstractTest : TestDependencies() {
    protected val authentication: UserRequestPostProcessor = SecurityMockMvcRequestPostProcessors.user("test").password("password")


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


    fun <T : Any> readContent(mvcResult: MvcResult, kClass: KClass<T>): T {
        return objectMapper.readValue(mvcResult.response.contentAsString, kClass.javaObjectType)
    }

}


