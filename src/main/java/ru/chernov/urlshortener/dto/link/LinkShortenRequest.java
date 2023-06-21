package ru.chernov.urlshortener.dto.link;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "https://google.com")
    @NotBlank
    @Length(max = 1000, message = "Link is too big")
    private String link;

    @Schema(example = "92f76c0c-2de1-4ad8-8116-10075572d564")
    @NotNull
    private UUID token;

}
