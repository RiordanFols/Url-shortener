package ru.chernov.urlshortener.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.helper.HttpHelper.postJson;


public class UserRegisterTest extends AbstractTest {
    private static final String TEST_USERNAME = "new_username";


    // TODO: на SQL скрипт
    @Test
    void registerUser() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest(TEST_USERNAME, "12345");
        mockMvc.perform(postJson(PATH_API_USERS)
                        .with(authentication)
                        .content(content(request)))
                .andExpect(status().isOk());

        assertTrue(userRepository.existsByUsername(TEST_USERNAME));
    }


    // TODO: на SQL скрипт
    @Test
    void usernameExist() throws Exception {
        UserRegisterRequest request = new UserRegisterRequest(TEST_USERNAME, "12345");
        mockMvc.perform(postJson(PATH_API_USERS)
                        .with(authentication)
                        .content(content(request)))
                .andExpect(status().isOk());

        mockMvc.perform(postJson(PATH_API_USERS)
                        .with(authentication)
                        .content(content(request)))
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException));
    }

}
