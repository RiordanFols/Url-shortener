package ru.chernov.urlshortener.dto.token;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenCreateRequest {

    @Schema(example = "new_token")
    @Size(max = 50)
    @NotNull
    private String name;

}
