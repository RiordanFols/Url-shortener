package ru.chernov.urlshortener.service.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.config.properties.LinkProperties;
import ru.chernov.urlshortener.exception.link.LinkNotFoundException;

import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


@Service
public class LinkService {
    private static final Logger logger = LogManager.getLogger(LinkService.class);

    private final LinkRedisService linkRedisService;
    private final LinkProperties linkProperties;


    public LinkService(LinkRedisService linkRedisService, LinkProperties linkProperties) {
        this.linkRedisService = linkRedisService;
        this.linkProperties = linkProperties;
    }


    public String shorten(String link) {
        String shortLink = getShortLink();
        linkRedisService.write(shortLink, link);
        return shortLink;
    }


    public String restore(String shortLink) {
        return linkRedisService.read(shortLink).orElseThrow(() -> {
            logger.error("Cannot found short link [{}].", shortLink);
            throw new LinkNotFoundException();
        });
    }


    private String getShortLink() {
        String shortLinkCode = nextAlphanumeric(linkProperties.getCodeLength());
        String shortLink = linkProperties.getPrefix() + shortLinkCode;
        return linkRedisService.read(shortLink).isEmpty() ? shortLink : getShortLink();
    }

}
