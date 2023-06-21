package ru.chernov.urlshortener.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "p.chernov")
    private String username;

}
