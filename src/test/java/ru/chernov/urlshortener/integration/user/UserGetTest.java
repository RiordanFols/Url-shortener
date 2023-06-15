package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;
import static ru.chernov.urlshortener.helper.HttpHelper.getJson;


public class UserGetTest extends AbstractTest {

    @Sql(value = {"/sql/clear.sql", "/sql/user/get/success.sql"},
            executionPhase = BEFORE_TEST_METHOD)
    @Test
    void success() throws Exception {
        long id = 9_000_000_100L;
        mockMvc.perform(getJson(PATH_API_USERS_ID, id)
                        .with(AUTHENTICATION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").isNotEmpty());
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/get/blocked.sql"},
            executionPhase = BEFORE_TEST_METHOD)
    @Test
    void blocked() throws Exception {
        long id = 9_000_000_101L;
        mockMvc.perform(getJson(PATH_API_USERS_ID, id)
                        .with(AUTHENTICATION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").isNotEmpty());
    }


    @Test
    void frozen() throws Exception {
        //TODO - 200
    }


    @Test
    void deleted() throws Exception {
        //TODO - 200
    }


    @Test
    void notFound() throws Exception {
        mockMvc.perform(getJson(PATH_API_USERS_ID, 9_999_999_999L)
                        .with(AUTHENTICATION))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserNotFoundException));
    }

}
