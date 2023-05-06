package ru.chernov.urlshortener.validation.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component
import ru.chernov.urlshortener.repository.UserRepository
import ru.chernov.urlshortener.validation.constraint.AvailableUsername

@Component
class AvailableUsernameValidator(
        private var userRepository: UserRepository
) : ConstraintValidator<AvailableUsername, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return !userRepository.existsByUsername(value!!)
    }
}