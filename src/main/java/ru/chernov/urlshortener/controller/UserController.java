package ru.chernov.urlshortener.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.chernov.urlshortener.dto.user.UserLevelRequest;
import ru.chernov.urlshortener.dto.user.UserRegisterRequest;
import ru.chernov.urlshortener.dto.user.UserResponse;
import ru.chernov.urlshortener.entity.user.User;
import ru.chernov.urlshortener.mapper.UserMapper;
import ru.chernov.urlshortener.service.user.UserService;

import static ru.chernov.urlshortener.consts.rest.PathVariables.USER_ID;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID;
import static ru.chernov.urlshortener.consts.rest.Routes.PATH_API_USERS_ID_LEVEL;


@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping(PATH_API_USERS_ID)
    public UserResponse get(@PathVariable(USER_ID) Long userId) {
        User user = userService.findById(userId);
        return userMapper.toResponse(user);
    }


    @PostMapping(PATH_API_USERS)
    public void register(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request);
    }


    @PutMapping(PATH_API_USERS_ID_LEVEL)
    public void updateLevel(@PathVariable(USER_ID) Long userId,
                            @RequestBody @Valid UserLevelRequest request) {
        userService.updateLevel(userId, request.getUserLevelName());
    }

}
