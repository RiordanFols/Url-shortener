package ru.chernov.urlshortener.integration.auth;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.AuthorizeRequest;
import ru.chernov.urlshortener.exception.user.UserNotFoundException;
import ru.chernov.urlshortener.exception.user.UserStatusException;
import ru.chernov.urlshortener.exception.user.UserWrongPasswordException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_AUTH;


public class JwtCreateTest extends AbstractTest {

    @Sql(value = {"/sql/clear.sql", "/sql/jwt/create/success.sql"})
    @Test
    void success() throws Exception {
        String username = "p.chernov";
        AuthorizeRequest request = new AuthorizeRequest(username, "PASSWORD");
        String jwt = mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(jwt);

        Claims claims = jwtService.extractAllClaims(jwt);
        assertEquals(username, claims.getSubject());
        assertNotNull(claims.getExpiration());
    }


    @Test
    void usernameNotFound() throws Exception {
        AuthorizeRequest request = new AuthorizeRequest("not_real_username_31fj9f131", "PASSWORD");
        mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserNotFoundException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/jwt/create/wrong-password.sql"})
    @Test
    void wrongPassword() throws Exception {
        AuthorizeRequest request = new AuthorizeRequest("p.chernov", "WRONG_PASSWORD");
        mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserWrongPasswordException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/jwt/create/user-deleted.sql"})
    @Test
    void userDeleted() throws Exception {
        AuthorizeRequest request = new AuthorizeRequest("p.chernov", "PASSWORD");
        mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserStatusException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/jwt/create/user-frozen.sql"})
    @Test
    void userFrozen() throws Exception {
        AuthorizeRequest request = new AuthorizeRequest("p.chernov", "PASSWORD");
        mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserStatusException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/jwt/create/user-blocked.sql"})
    @Test
    void userBlocked() throws Exception {
        AuthorizeRequest request = new AuthorizeRequest("p.chernov", "PASSWORD");
        mockMvc.perform(postJson(PATH_AUTH)
                        .content(content(request)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserStatusException));
    }

}
