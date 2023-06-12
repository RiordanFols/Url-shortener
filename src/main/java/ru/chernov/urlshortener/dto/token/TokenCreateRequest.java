package ru.chernov.urlshortener.dto.token;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenCreateRequest {

    @Size(max = 50)
    @NotNull
    private String name;

}
