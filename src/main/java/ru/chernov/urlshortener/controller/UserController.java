package ru.chernov.urlshortener.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;
import ru.chernov.urlshortener.dto.user.UserResponse;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.mapper.UserMapper;
import ru.chernov.urlshortener.service.user.UserService;

import static ru.chernov.urlshortener.consts.rest.PathVariables.USER_ID;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;


@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping(PATH_API_USERS_ID)
    public UserResponse get(@PathVariable(USER_ID) Long userId) {
        User user = userService.findById(userId);
        return userMapper.toResponse(user);
    }

    
    @PostMapping(PATH_API_USERS)
    public void register(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request);
    }

}
