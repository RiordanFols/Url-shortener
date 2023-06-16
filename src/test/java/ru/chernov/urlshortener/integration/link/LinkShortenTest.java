package ru.chernov.urlshortener.integration.link;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.exception.operation.TooManyMinuteOperationsException;
import ru.chernov.urlshortener.exception.operation.TooManyMonthOperationsException;
import ru.chernov.urlshortener.exception.token.TokenNotFoundException;
import ru.chernov.urlshortener.exception.token.TokenStatusException;
import ru.chernov.urlshortener.exception.user.UserStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_LINKS;
import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


public class LinkShortenTest extends AbstractTest {
    private static final UUID TEST_UUID = UUID.fromString("92f76c0c-2de1-4ad8-8116-10075572d564");
    private static final LinkShortenRequest REQUEST = new LinkShortenRequest("https://google.com", TEST_UUID);


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/success.sql"})
    @Test
    void success() throws Exception {
        String shortLink = mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(linkRedisService.read(shortLink));
        assertEquals(1, operationRepository.findAll().size());
    }


    @Test
    void tooLongLink() throws Exception {
        LinkShortenRequest request = new LinkShortenRequest(nextAlphanumeric(1002), TEST_UUID);
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(request)))
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException));
    }


    @Test
    void notFoundToken() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TokenNotFoundException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/frozen-token.sql"})
    @Test
    void frozenToken() throws Exception {
        tokenStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/deleted-token.sql"})
    @Test
    void deletedToken() throws Exception {
        tokenStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/blocked-token.sql"})
    @Test
    void blockedToken() throws Exception {
        tokenStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/frozen-user.sql"})
    @Test
    void frozenUser() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isOk());
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/deleted-user.sql"})
    @Test
    void deletedUser() throws Exception {
        userStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/blocked-user.sql"})
    @Test
    void blockedUser() throws Exception {
        userStatusException();
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/too-many-minute-operations.sql"})
    @Test
    void tooManyMinuteOperations() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TooManyMinuteOperationsException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/too-many-month-operations.sql"})
    @Test
    void tooManyMonthOperations() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TooManyMonthOperationsException));
    }


    private void tokenStatusException() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TokenStatusException));
    }


    private void userStatusException() throws Exception {
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(REQUEST)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserStatusException));
    }

}
