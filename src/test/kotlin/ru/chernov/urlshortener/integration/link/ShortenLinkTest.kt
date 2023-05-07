package ru.chernov.urlshortener.integration.link

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.bind.MethodArgumentNotValidException
import ru.chernov.urlshortener.AbstractTest
import ru.chernov.urlshortener.consts.rest.PATH_API_LINKS
import ru.chernov.urlshortener.dto.link.LinkShortenRequest
import ru.chernov.urlshortener.helper.postJson
import ru.chernov.urlshortener.utils.nextAlphanumeric

class ShortenLinkTest : AbstractTest() {

    @Test
    fun shortenLink() {
        val request = LinkShortenRequest("https://google.com")
        val shortLink = mockMvc.perform(postJson(PATH_API_LINKS)
                .with(authentication)
                .content(content(request)))
                .andExpect(status().isOk)
                .andReturn()
                .response
                .contentAsString

        assertNotNull(linkRedisService.read(shortLink))
    }


    @Test
    fun tooLongLink() {
        val request = LinkShortenRequest(nextAlphanumeric(1002))
        mockMvc.perform(postJson(PATH_API_LINKS)
                .with(authentication)
                .content(content(request)))
                .andExpect(status().isBadRequest)
                .andExpect { res -> assertTrue(res.resolvedException is MethodArgumentNotValidException) }
    }

}