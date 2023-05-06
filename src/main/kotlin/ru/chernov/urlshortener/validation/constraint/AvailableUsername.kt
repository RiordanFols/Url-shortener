package ru.chernov.urlshortener.validation.constraint

import jakarta.validation.Constraint
import jakarta.validation.Payload
import ru.chernov.urlshortener.validation.validator.AvailableUsernameValidator
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [AvailableUsernameValidator::class])
annotation class AvailableUsername(
        val message: String = "Username already exists",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)