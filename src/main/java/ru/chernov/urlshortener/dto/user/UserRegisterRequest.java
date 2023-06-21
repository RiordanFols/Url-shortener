package ru.chernov.urlshortener.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chernov.urlshortener.validation.constraint.AvailableUsername;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterRequest {

    @Schema(example = "p.chernov")
    @AvailableUsername
    @NotNull
    private String username;

    @Schema(example = "qwerty")
    @NotNull
    private String password;

}
