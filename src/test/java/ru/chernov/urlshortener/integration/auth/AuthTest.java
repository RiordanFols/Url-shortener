package ru.chernov.urlshortener.integration.auth;

import org.junit.jupiter.api.Test;
import ru.chernov.urlshortener.AbstractTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;


public class AuthTest extends AbstractTest {

    @Test
    void success() throws Exception {
        mockMvc.perform(getJson(PATH_API_USERS_ID, 9000000000L)
                        .headers(httpHeaders()))
                .andExpect(status().isOk());
    }


    @Test
    void notAuthorized() throws Exception {
        mockMvc.perform(getJson(PATH_API_USERS_ID, 9000000000L))
                .andExpect(status().isUnauthorized());
    }

}
