package ru.chernov.urlshortener.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.token.TokenCreateRequest;
import ru.chernov.urlshortener.entity.Token;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.enums.token.TokenStatus;
import ru.chernov.urlshortener.exception.token.TokenNotFoundException;
import ru.chernov.urlshortener.exception.token.TokenStatusException;
import ru.chernov.urlshortener.repository.TokenRepository;
import ru.chernov.urlshortener.service.user.UserService;

import java.util.UUID;

import static ru.chernov.urlshortener.utils.TimeUtil.utcNow;


@Service
public class TokenService {
    private static final Logger logger = LogManager.getLogger(TokenService.class);

    private final UserService userService;
    private final TokenRepository tokenRepository;


    public TokenService(UserService userService,
                        TokenRepository tokenRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }


    public Token find(UUID value) {
        return tokenRepository.findById(value.toString()).orElseThrow(() -> {
            logger.error("Token [{}] not found.", value);
            throw new TokenNotFoundException();
        });
    }


    public void validate(UUID value) {
        TokenStatus status = find(value).getStatus();
        if (status != TokenStatus.ACTIVE) {
            logger.error("Token [{}] has wrong status [{}].", value, status.name());
            throw new TokenStatusException(status);
        }
    }


    public void create(TokenCreateRequest createRequest) {
        User user = userService.getCurrent();

        var token = new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());
        token.setCreatedAt(utcNow());
        token.setStatus(TokenStatus.ACTIVE);
        token.setName(createRequest.getName());
        tokenRepository.save(token);
    }

}
