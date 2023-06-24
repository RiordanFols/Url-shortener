package ru.chernov.urlshortener.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chernov.urlshortener.config.security.jwt.JwtService;
import ru.chernov.urlshortener.dto.AuthorizeRequest;

import static ru.chernov.urlshortener.consts.rest.Routes.PATH_AUTH;


@RestController
public class JwtController {
    private final JwtService jwtService;


    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Operation(summary = "Create JWT")
    @ApiResponse(responseCode = "200", description = "Success jwt creation", content = @Content)
    @ApiResponse(responseCode = "400", description = "Invalid request / wrong password", content = @Content)
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @PostMapping(PATH_AUTH)
    public String create(@RequestBody @Valid AuthorizeRequest request) {
        return jwtService.create(request.getUsername(), request.getPassword());
    }

}
