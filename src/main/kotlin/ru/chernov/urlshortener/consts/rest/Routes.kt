package ru.chernov.urlshortener.consts.rest

const val PATH: String = "/"
const val PATH_API: String = PATH + "api"

// LINKS
const val PATH_SHORT_LINK: String = "$PATH/{$SHORT_LINK}"
const val PATH_API_LINKS: String = "$PATH_API/links"

// USERS
const val PATH_API_USERS: String = "$PATH_API/users"
const val PATH_API_USERS_ID: String = "$PATH_API_USERS/{$USER_ID}"
