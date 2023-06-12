package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.enums.user.UserStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.helper.HttpHelper.postJson;


public class UserRegisterTest extends AbstractTest {
    private static final String TEST_USERNAME = "new_username";


    @Test
    void registerUser() throws Exception {
        String password = "12345";
        UserRegisterRequest request = new UserRegisterRequest(TEST_USERNAME, password);
        mockMvc.perform(postJson(PATH_API_USERS)
                        .content(content(request)))
                .andExpect(status().isOk());

        User user = userService.loadUserByUsername(TEST_USERNAME);
        assertEquals(TEST_USERNAME, user.getUsername());
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
        assertEquals(UserStatus.ACTIVE, user.getStatus());
    }


    @Sql(value = {"/sql/clear.sql", "/sql/user/register/username-exist-before.sql"},
            executionPhase = BEFORE_TEST_METHOD)
    @Test
    void usernameExist() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest(TEST_USERNAME, "12345");
        mockMvc.perform(postJson(PATH_API_USERS)
                        .content(content(request)))
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException));
    }

}
