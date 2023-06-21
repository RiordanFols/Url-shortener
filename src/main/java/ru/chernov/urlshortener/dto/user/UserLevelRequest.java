package ru.chernov.urlshortener.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chernov.urlshortener.enums.user.UserLevelName;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLevelRequest {

    @Schema(example = "NONE", implementation = UserLevelName.class)
    @NotNull
    private UserLevelName userLevelName;

}
