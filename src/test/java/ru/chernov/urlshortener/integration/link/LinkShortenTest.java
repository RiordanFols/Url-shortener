package ru.chernov.urlshortener.integration.link;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.exception.token.TokenNotFoundException;
import ru.chernov.urlshortener.exception.token.TokenStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_LINKS;
import static ru.chernov.urlshortener.helper.HttpHelper.postJson;
import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


public class LinkShortenTest extends AbstractTest {
    private static final UUID TEST_UUID = UUID.fromString("92f76c0c-2de1-4ad8-8116-10075572d564");


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/shorten-link-before.sql"},
            executionPhase = BEFORE_TEST_METHOD)
    @Test
    void success() throws Exception {
        LinkShortenRequest request = new LinkShortenRequest("https://google.com", TEST_UUID);
        String shortLink = mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(linkRedisService.read(shortLink));
        assertEquals(1, operationRepository.findAll().size());
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/too-long-link-before.sql"},
            executionPhase = BEFORE_TEST_METHOD)
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
        LinkShortenRequest request = new LinkShortenRequest("https://google.com", TEST_UUID);
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(request)))
                .andExpect(status().isNotFound())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TokenNotFoundException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/shorten/wrong-status-token-before.sql"},
            executionPhase = BEFORE_TEST_METHOD)
    @Test
    void wrongStatusToken() throws Exception {
        LinkShortenRequest request = new LinkShortenRequest("https://google.com", TEST_UUID);
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .content(content(request)))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TokenStatusException));
    }


    @Test
    void frozenUser() throws Exception {
        // TODO: 200
    }


    @Test
    void deletedUser() throws Exception {
        // TODO: 409
    }


    @Test
    void blockedUser() throws Exception {
        // TODO: 409
    }


    @Test
    void tooManyOperationsPerMinute() throws Exception {
        // TODO - 409
    }


    @Test
    void tooManyOperationsPerMonth() throws Exception {
        // TODO - 409
    }

}
