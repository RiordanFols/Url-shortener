package ru.chernov.urlshortener.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private UUID token;

}
