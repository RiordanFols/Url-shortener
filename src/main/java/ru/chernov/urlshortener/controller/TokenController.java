package ru.chernov.urlshortener.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chernov.urlshortener.dto.token.TokenCreateRequest;
import ru.chernov.urlshortener.service.TokenService;

import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_TOKENS;


@RestController
public class TokenController {
    private final TokenService tokenService;


    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @ApiResponse(responseCode = "200", description = "Token created", content = @Content)
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Current user not found", content = @Content)
    @PostMapping(PATH_API_TOKENS)
    public void create(@RequestBody @Valid TokenCreateRequest createRequest) {
        tokenService.create(createRequest);
    }

}
