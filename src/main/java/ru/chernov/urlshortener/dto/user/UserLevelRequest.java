package ru.chernov.urlshortener.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chernov.urlshortener.enums.user.UserLevelName;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLevelRequest {
    @NotNull
    private UserLevelName userLevelName;

}
