package ru.chernov.urlshortener.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.chernov.urlshortener.validation.constraint.AvailableUsername;


@AllArgsConstructor
@Data
public class UserRegisterRequest {

    @AvailableUsername
    @NotNull
    private String username;

    @NotNull
    private String password;

}
