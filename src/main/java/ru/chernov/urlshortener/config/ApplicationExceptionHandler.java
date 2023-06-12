package ru.chernov.urlshortener.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;
import ru.chernov.urlshortener.service.LocalizationService;

import java.util.Map;


@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final String MESSAGE_FIELD = "message";

    private final LocalizationService localizationService;


    public ApplicationExceptionHandler(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }


    @ExceptionHandler(LocalizableResponseStatusException.class)
    public ResponseEntity<?> handle(LocalizableResponseStatusException e) {
        String localizedMessage = localizationService.localize(e.getPrefix() + "." + e.getCode());
        return ResponseEntity
                .status(e.getStatusCode())
                .body(Map.of(MESSAGE_FIELD, localizedMessage));
    }

}
