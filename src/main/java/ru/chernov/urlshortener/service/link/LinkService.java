package ru.chernov.urlshortener.service.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chernov.urlshortener.config.properties.LinkProperties;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.dto.link.RedisLinkDto;
import ru.chernov.urlshortener.enums.operation.OperationType;
import ru.chernov.urlshortener.exception.link.LinkNotFoundException;
import ru.chernov.urlshortener.service.OperationService;
import ru.chernov.urlshortener.service.TokenService;

import java.util.UUID;

import static ru.chernov.urlshortener.utils.StringRandomizer.nextAlphanumeric;


@Service
public class LinkService {
    private static final Logger logger = LogManager.getLogger(LinkService.class);

    private final TokenService tokenService;
    private final OperationService operationService;
    private final LinkRedisService linkRedisService;
    private final LinkProperties linkProperties;


    public LinkService(TokenService tokenService,
                       OperationService operationService,
                       LinkRedisService linkRedisService,
                       LinkProperties linkProperties) {
        this.tokenService = tokenService;
        this.operationService = operationService;
        this.linkRedisService = linkRedisService;
        this.linkProperties = linkProperties;
    }


    @Transactional
    public String restore(String shortLink) {
        RedisLinkDto redisLink = linkRedisService.read(linkProperties.getPrefix() + shortLink).orElseThrow(() -> {
            logger.error("Cannot found short link [{}].", shortLink);
            throw new LinkNotFoundException();
        });

        operationService.addOperation(OperationType.REDIRECT, redisLink.getToken());
        return redisLink.getLink();
    }


    @Transactional
    public String shorten(LinkShortenRequest request) {
        UUID token = request.getToken();
        tokenService.validate(token);

        operationService.addOperation(OperationType.SHORTEN, token);

        String shortLink = getShortLink();
        linkRedisService.write(shortLink, request.getLink(), token);
        return shortLink;
    }


    private String getShortLink() {
        String shortLinkCode = nextAlphanumeric(linkProperties.getCodeLength());
        String shortLink = linkProperties.getPrefix() + shortLinkCode;
        return linkRedisService.read(shortLink).isEmpty() ? shortLink : getShortLink();
    }

}
