package ru.chernov.urlshortener.dto.link;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@AllArgsConstructor
@Data
public class LinkShortenRequest {

    @NotBlank
    @Length(max = 1000, message = "Link is too big")
    private String link;

}
