package ru.chernov.urlshortener.integration.token;

import org.junit.jupiter.api.Test;
import ru.chernov.urlshortener.AbstractTest;
import ru.chernov.urlshortener.dto.token.TokenCreateRequest;
import ru.chernov.urlshortener.entity.Token;
import ru.chernov.urlshortener.enums.token.TokenStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_TOKENS;
import static ru.chernov.urlshortener.helper.HttpHelper.postJson;


public class TokenCreateTest extends AbstractTest {

    @Test
    void success() throws Exception {
        String tokenName = "My token";
        mockMvc.perform(postJson(PATH_API_TOKENS)
                        .with(AUTHENTICATION)
                        .content(content(new TokenCreateRequest(tokenName))))
                .andExpect(status().isOk());

        List<Token> tokens = tokenRepository.findAll();
        assertEquals(1, tokens.size());
        Token token = tokens.get(0);
        assertEquals(tokenName, token.getName());
        assertEquals(TEST_USER_ID, token.getUser().getId());
        assertEquals(TokenStatus.ACTIVE, token.getStatus());
    }


    @Test
    void tooLongName() throws Exception {
        String tokenName = "Tooooooooooooooooooooo looooooooooooooooooong token name";
        mockMvc.perform(postJson(PATH_API_TOKENS)
                        .with(AUTHENTICATION)
                        .content(content(new TokenCreateRequest(tokenName))))
                .andExpect(status().isBadRequest());
    }

}
