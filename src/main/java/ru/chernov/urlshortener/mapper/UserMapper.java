package ru.chernov.urlshortener.mapper;

import org.mapstruct.Mapper;
import ru.chernov.urlshortener.dto.user.UserResponse;
import ru.chernov.urlshortener.entity.user.User;


@Mapper
public interface UserMapper {

    UserResponse toResponse(User user);

}
