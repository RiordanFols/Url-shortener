package ru.chernov.urlshortener.integration.link;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_LINKS;
import static ru.chernov.urlshortener.helper.HttpHelper.postJson;
import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


public class LinkShortenTest extends AbstractTest {

    @Test
    void shortenLink() throws Exception {
        LinkShortenRequest request = new LinkShortenRequest("https://google.com");
        String shortLink = mockMvc.perform(postJson(PATH_API_LINKS)
                        .with(AUTHENTICATION)
                        .content(content(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(linkRedisService.read(shortLink));
        assertEquals(1, operationRepository.findAll().size());
    }


    @Test
    void tooLongLink() throws Exception {
        LinkShortenRequest request = new LinkShortenRequest(nextAlphanumeric(1002));
        mockMvc.perform(postJson(PATH_API_LINKS)
                        .with(AUTHENTICATION)
                        .content(content(request)))
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException));
    }

}
