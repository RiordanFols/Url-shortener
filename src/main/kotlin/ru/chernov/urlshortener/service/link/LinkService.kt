package ru.chernov.urlshortener.service.link

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service
import ru.chernov.urlshortener.config.properties.LinkProperties
import ru.chernov.urlshortener.exception.link.LinkNotFoundException
import ru.chernov.urlshortener.utils.nextAlphanumeric


@Service
class LinkService(
        private val linkRedisService: LinkRedisService,
        private val linkProperties: LinkProperties
) {
    private val logger: Logger = LogManager.getLogger(LinkService::class)

    
    fun shorten(link: String): String {
        val shortLink: String = getShortLink()
        linkRedisService.write(shortLink, link)
        return shortLink
    }


    fun restore(shortLink: String): String {
        return linkRedisService.read(shortLink) ?: run {
            logger.error("Cannot found short link [{}].", shortLink)
            throw LinkNotFoundException()
        }
    }


    private fun getShortLink(): String {
        val shortLinkCode = nextAlphanumeric(linkProperties.codeLength)
        val shortLink = linkProperties.prefix + shortLinkCode
        return if (linkRedisService.read(shortLink) == null) shortLink else getShortLink()
    }

}