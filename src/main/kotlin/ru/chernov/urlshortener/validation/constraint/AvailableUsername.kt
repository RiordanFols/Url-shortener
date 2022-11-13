package ru.chernov.urlshortener.validation.constraint

import jakarta.validation.Constraint
import ru.chernov.urlshortener.validation.validator.AvailableUsernameValidator


// TODO: error message
@Constraint(validatedBy = [AvailableUsernameValidator::class])
annotation class AvailableUsername