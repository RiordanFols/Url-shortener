package ru.chernov.urlshortener.integration.link;


import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.exception.operation.TooManyMinuteOperationsException;
import ru.chernov.urlshortener.exception.operation.TooManyMonthOperationsException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.helper.HttpHelper.getJson;


public class LinkRedirectTest extends AbstractTest {

    @Sql(value = {"/sql/clear.sql", "/sql/link/redirect/success.sql"})
    @Test
    void success() throws Exception {
        UUID tokenValue = UUID.fromString("92f76c0c-2de1-4ad8-8116-10075572d564");
        LinkShortenRequest shortenRequest = new LinkShortenRequest("https://www.google.com/", tokenValue);
        String shortLink = linkService.shorten(shortenRequest);

        mockMvc.perform(getJson(shortLink))
                .andExpect(status().isFound());
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/redirect/too-many-minute-operations.sql"})
    @Test
    void tooManyMinuteOperations() throws Exception {
        UUID tokenValue = UUID.fromString("92f76c0c-2de1-4ad8-8116-10075572d564");
        LinkShortenRequest shortenRequest = new LinkShortenRequest("https://www.google.com/", tokenValue);
        String shortLink = linkService.shorten(shortenRequest);

        mockMvc.perform(getJson(shortLink))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TooManyMinuteOperationsException));
    }


    @Sql(value = {"/sql/clear.sql", "/sql/link/redirect/too-many-month-operations.sql"})
    @Test
    void tooManyMonthOperations() throws Exception {
        UUID tokenValue = UUID.fromString("92f76c0c-2de1-4ad8-8116-10075572d564");
        LinkShortenRequest shortenRequest = new LinkShortenRequest("https://www.google.com/", tokenValue);
        String shortLink = linkService.shorten(shortenRequest);

        mockMvc.perform(getJson(shortLink))
                .andExpect(status().isConflict())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof TooManyMonthOperationsException));
    }

}
