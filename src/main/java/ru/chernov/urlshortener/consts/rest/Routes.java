package ru.chernov.urlshortener.consts.rest;

public class Routes {
    // AUTH
    public static final String PATH_AUTH = "/api/auth";

    // LINKS
    public static final String PATH_SHORT_LINK = "/{SHORT_LINK}";
    public static final String PATH_API_LINKS = "/api/links";

    // USERS
    public static final String PATH_API_USERS = "/api/users";
    public static final String PATH_API_USERS_ID = "/api/users/{USER_ID}";
    public static final String PATH_API_USERS_ID_LEVEL = "/api/users/{USER_ID}/level";

    // TOKENS
    public static final String PATH_API_TOKENS = "/api/tokens";

}
