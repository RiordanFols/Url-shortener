package ru.chernov.urlshortener.dto.link;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkShortenRequest {

    @NotBlank
    @Length(max = 1000, message = "Link is too big")
    private String link;

}
