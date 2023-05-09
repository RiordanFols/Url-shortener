package ru.chernov.urlshortener.mapper;

import org.springframework.stereotype.Component;
import ru.chernov.urlshortener.dto.user.UserResponse;
import ru.chernov.urlshortener.entity.user.User;


// TODO: add mapstruct
@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getToken());
    }

}
