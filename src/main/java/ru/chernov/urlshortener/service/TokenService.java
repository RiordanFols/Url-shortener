package ru.chernov.urlshortener.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.entity.Token;
import ru.chernov.urlshortener.exception.token.TokenNotFoundException;
import ru.chernov.urlshortener.repository.TokenRepository;

import java.util.UUID;


@Service
public class TokenService {
    private static final Logger logger = LogManager.getLogger(TokenService.class);

    private final TokenRepository tokenRepository;


    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public Token find(UUID value) {
        return tokenRepository.findById(value.toString()).orElseThrow(() -> {
            logger.error("Token [{}] not found.", value);
            throw new TokenNotFoundException();
        });
    }


    public void validate(UUID value) {
        Token token = find(value);
        // TODO: validate token for status (and add status to token)
    }

}
