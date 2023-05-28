package ru.chernov.urlshortener.service.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chernov.urlshortener.config.properties.LinkProperties;
import ru.chernov.urlshortener.enums.operation.OperationType;
import ru.chernov.urlshortener.exception.link.LinkNotFoundException;
import ru.chernov.urlshortener.service.OperationService;

import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


@Service
public class LinkService {
    private static final Logger logger = LogManager.getLogger(LinkService.class);

    private final OperationService operationService;
    private final LinkRedisService linkRedisService;
    private final LinkProperties linkProperties;


    public LinkService(OperationService operationService,
                       LinkRedisService linkRedisService,
                       LinkProperties linkProperties) {
        this.operationService = operationService;
        this.linkRedisService = linkRedisService;
        this.linkProperties = linkProperties;
    }


    @Transactional
    public String restore(String shortLink) {
        operationService.addOperation(OperationType.REDIRECT);

        return linkRedisService.read(linkProperties.getPrefix() + shortLink).orElseThrow(() -> {
            logger.error("Cannot found short link [{}].", shortLink);
            throw new LinkNotFoundException();
        });
    }


    @Transactional
    public String shorten(String link) {
        operationService.addOperation(OperationType.SHORTEN);

        String shortLink = getShortLink();
        linkRedisService.write(shortLink, link);
        return shortLink;
    }


    private String getShortLink() {
        String shortLinkCode = nextAlphanumeric(linkProperties.getCodeLength());
        String shortLink = linkProperties.getPrefix() + shortLinkCode;
        return linkRedisService.read(shortLink).isEmpty() ? shortLink : getShortLink();
    }

}
