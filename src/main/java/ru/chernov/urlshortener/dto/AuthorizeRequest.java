package ru.chernov.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeRequest {

    @Schema(example = "p.chernov")
    @NotNull
    private String username;

    @Schema(example = "qwerty")
    @NotNull
    private String password;

}
