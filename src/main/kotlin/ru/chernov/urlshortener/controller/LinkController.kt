package ru.chernov.urlshortener.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import ru.chernov.urlshortener.consts.rest.PATH_API_LINKS
import ru.chernov.urlshortener.consts.rest.PATH_SHORT_LINK
import ru.chernov.urlshortener.consts.rest.SHORT_LINK
import ru.chernov.urlshortener.dto.link.LinkShortenRequest
import ru.chernov.urlshortener.service.link.LinkService


@RestController
class LinkController(val linkService: LinkService) {

    @GetMapping(PATH_SHORT_LINK)
    fun redirect(@PathVariable(SHORT_LINK) shortLink: String): RedirectView {
        return RedirectView(linkService.restore(shortLink))
    }


    @PostMapping(PATH_API_LINKS)
    fun shorten(@Valid @RequestBody request: LinkShortenRequest): String {
        return linkService.shorten(request.link)
    }

}