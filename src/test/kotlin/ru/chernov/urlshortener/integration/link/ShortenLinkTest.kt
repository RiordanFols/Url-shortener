package ru.chernov.urlshortener.integration.link

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import ru.chernov.urlshortener.AbstractTest
import ru.chernov.urlshortener.consts.rest.PATH_API_LINKS
import ru.chernov.urlshortener.dto.link.LinkShortenRequest
import ru.chernov.urlshortener.helper.postJson

class ShortenLinkTest : AbstractTest() {

    @Test
    fun shouldShortenLink() {
        val link = "https://google.com"
        val request = LinkShortenRequest(link)
        val shortLink = mockMvc.perform(postJson(PATH_API_LINKS)
                .with(authentication)
                .content(content(request)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        assertNotNull(linkRedisService.read(shortLink))
    }

}