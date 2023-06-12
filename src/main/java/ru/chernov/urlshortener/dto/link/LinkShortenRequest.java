package ru.chernov.urlshortener.dto.link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkShortenRequest {

    @NotBlank
    @Length(max = 1000, message = "Link is too big")
    private String link;

    @NotNull
    private UUID token;

}
