package ru.chernov.urlshortener.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.chernov.urlshortener.repository.user.UserRepository;
import ru.chernov.urlshortener.validation.constraint.AvailableUsername;


@Component
public class AvailableUsernameValidator implements ConstraintValidator<AvailableUsername, String> {
    private static final Logger logger = LogManager.getLogger(AvailableUsernameValidator.class);

    private final UserRepository userRepository;


    public AvailableUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.existsByUsername(username)) {
            logger.error("Username [{}] already exist", username);
            return false;
        }
        return true;
    }

}
