package ru.chernov.urlshortener.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.chernov.urlshortener.dto.link.LinkShortenRequest;
import ru.chernov.urlshortener.service.link.LinkService;

import static ru.chernov.urlshortener.consts.rest.PathVariables.SHORT_LINK;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_LINKS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_SHORT_LINK;


@RestController
public class LinkController {
    private final LinkService linkService;


    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }


    @GetMapping(PATH_SHORT_LINK)
    public RedirectView redirect(@PathVariable(SHORT_LINK) String shortLink) {
        return new RedirectView(linkService.restore(shortLink));
    }


    @PostMapping(PATH_API_LINKS)
    public String shorten(@Valid @RequestBody LinkShortenRequest request) {
        return linkService.shorten(request);
    }

}
