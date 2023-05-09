package ru.chernov.urlshortener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@ExtendWith(SpringExtension.class)
@Sql(value = "/sql/clear.sql", executionPhase = BEFORE_TEST_METHOD)
public class AbstractTest extends TestDependencies {
    // TODO:
    protected UserRequestPostProcessor authentication = SecurityMockMvcRequestPostProcessors.user("test").password("password");


    @BeforeEach
    protected void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .addFilter(((request, response, chain) -> {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }))
                .apply(springSecurity())
                .build();
    }


    protected byte[] content(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(object);
    }


    protected <T> T readContent(MvcResult mvcResult, Class<T> clazz) throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), clazz);
    }

}
