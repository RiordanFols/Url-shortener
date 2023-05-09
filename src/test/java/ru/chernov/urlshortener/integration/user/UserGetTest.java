package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;
import static ru.chernov.urlshortener.helper.HttpHelper.getJson;


public class UserGetTest extends AbstractTest {

    @Sql(value = "/sql/user/get-before.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(value = "/sql/user/get-after.sql", executionPhase = AFTER_TEST_METHOD)
    @Test
    void get() throws Exception {
        long id = 9_000_000_100L;
        mockMvc.perform(getJson(PATH_API_USERS_ID, id)
                        .with(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").isNotEmpty())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

}
