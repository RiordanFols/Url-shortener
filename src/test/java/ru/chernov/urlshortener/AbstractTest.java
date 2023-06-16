package ru.chernov.urlshortener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@ExtendWith(SpringExtension.class)
@Sql(value = "/sql/clear.sql")
public class AbstractTest extends TestDependencies {
    protected static final Long TEST_USER_ID = 9_000_000_000L;
    protected static final UserRequestPostProcessor AUTHENTICATION = SecurityMockMvcRequestPostProcessors.user("TEST_USER").password("PASSWORD");


    @BeforeEach
    protected void setUp() {
        redissonClient.getKeys().deleteByPattern("*");
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


    protected static MockHttpServletRequestBuilder getJson(String url, Object... urlVariables) {
        return get(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }


    protected static MockHttpServletRequestBuilder postJson(String url, Object... urlVariables) {
        return post(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }


    protected static MockHttpServletRequestBuilder putJson(String url, Object... urlVariables) {
        return put(url, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }

}
