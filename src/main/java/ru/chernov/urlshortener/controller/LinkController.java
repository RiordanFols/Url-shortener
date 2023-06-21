package ru.chernov.urlshortener.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.service.link.LinkService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.chernov.urlshortener.consts.rest.PathVariables.SHORT_LINK;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_LINKS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_SHORT_LINK;


@RestController
public class LinkController {
    private final LinkService linkService;


    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }


    @Operation(summary = "Redirect by short link")
    @ApiResponse(responseCode = "302", description = "Success redirect", content = @Content)
    @ApiResponse(responseCode = "400", description = "Invalid link", content = @Content)
    @ApiResponse(responseCode = "404", description = "Link/Token not found", content = @Content)
    @ApiResponse(responseCode = "409", description = "Too many operations for token", content = @Content)
    @GetMapping(PATH_SHORT_LINK)
    public RedirectView redirect(@PathVariable(SHORT_LINK) @Parameter(example = "et3u1p") String shortLink) {
        return new RedirectView(linkService.restore(shortLink));
    }


    @Operation(summary = "Shorten link")
    @ApiResponse(responseCode = "200", description = "Success shorten",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(example = "http://localhost:8080/et3u1p"))})
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Token not found", content = @Content)
    @ApiResponse(responseCode = "409", description = "Invalid token/user or too many operations for token", content = @Content)
    @PostMapping(PATH_API_LINKS)
    public String shorten(@Valid @RequestBody LinkShortenRequest request) {
        return linkService.shorten(request);
    }

}
