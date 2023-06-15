package ru.chernov.urlshortener.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.chernov.urlshortener.enums.user.UserLevelName;


@Data
public class UserLevelRequest {
    @NotNull
    private UserLevelName userLevelName;

}
