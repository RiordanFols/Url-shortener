package ru.chernov.urlshortener.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.chernov.urlshortener.validation.validator.AvailableUsernameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AvailableUsernameValidator.class)
public @interface AvailableUsername {

    String message() default "Username already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
