package ru.chernov.urlshortener.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private UUID token;

}
