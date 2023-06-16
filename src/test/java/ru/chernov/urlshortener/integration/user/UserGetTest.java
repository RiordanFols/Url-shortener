package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;


public class UserGetTest extends AbstractTest {
    private static final Long TEST_USER_ID = 9_000_000_101L;


    @Sql(value = {"/sql/clear.sql", "/sql/user/get/success.sql"})
    @Test
    void success() throws Exception {
        userFound();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/get/blocked.sql"})
    @Test
    void blocked() throws Exception {
        userFound();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/get/frozen.sql"})
    @Test
    void frozen() throws Exception {
        userFound();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/get/deleted.sql"})
    @Test
    void deleted() throws Exception {
        userFound();
    }


    @Test
    void notFound() throws Exception {
        mockMvc.perform(getJson(PATH_API_USERS_ID, 9_999_999_999L)
                        .with(AUTHENTICATION))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserNotFoundException));
    }


    private void userFound() throws Exception {
        mockMvc.perform(getJson(PATH_API_USERS_ID, TEST_USER_ID)
                        .with(AUTHENTICATION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(TEST_USER_ID))
                .andExpect(jsonPath("$.username").isNotEmpty());
    }

}
