package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.user.UserLevelRequest;
import ru.chernov.urlshortener.enums.user.UserLevelName;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;
import ru.chernov.urlshortener.exception.user.UserStatusException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID_LEVEL;
import static ru.chernov.urlshortener.helper.HttpHelper.putJson;


public class UserUpdateLevelTest extends AbstractTest {
    private static final Long TEST_USER_ID = 9_000_000_101L;
    private static final UserLevelRequest REQUEST = new UserLevelRequest(UserLevelName.SILVER);


    @Sql(value = {"/sql/clear.sql", "/sql/user/update-level/success.sql"})
    @Test
    void success() throws Exception {
        mockMvc.perform(putJson(PATH_API_USERS_ID_LEVEL, TEST_USER_ID)
                        .with(AUTHENTICATION)
                        .content(content(REQUEST)))
                .andExpect(status().isOk());
    }


    @Test
    void notFound() throws Exception {
        mockMvc.perform(putJson(PATH_API_USERS_ID_LEVEL, 9_999_999_999L)
                        .with(AUTHENTICATION)
                        .content(content(REQUEST)))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserNotFoundException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/update-level/deleted-user.sql"})
    @Test
    void deletedUser() throws Exception {
        userStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/update-level/blocked-user.sql"})
    @Test
    void blockedUser() throws Exception {
        userStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/update-level/frozen-user.sql"})
    @Test
    void frozenUser() throws Exception {
        userStatusException();
    }


    private void userStatusException() throws Exception {
        mockMvc.perform(putJson(PATH_API_USERS_ID_LEVEL, TEST_USER_ID)
                        .with(AUTHENTICATION)
                        .content(content(REQUEST)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserStatusException));
    }

}
