package com.hustletech.template.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import com.hustletech.template.shared.exception.BadRequestException;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate that a given string value is a properly
 * formatted name,
 * which must include at least a first name and a surname.
 */
@Documented
@Constraint(validatedBy = NameValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {
    String message() default "Name must contain a first name and a surname.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validator to enforce that the given name includes both a first name and a
     * surname.
     */
    class Validator implements ConstraintValidator<NameValidation, String> {
        @Override
        public boolean isValid(String name, ConstraintValidatorContext context) {
            List<String> errors = new ArrayList<>();

            if (!isNotEmpty(name)) {
                errors.add("Name cannot be empty.");
            }

            if (name != null && !containsFirstNameAndSurname(name)) {
                errors.add("Name must include both a first name and a surname.");
            }

            if (!errors.isEmpty()) {
                throw new BadRequestException(errors); // Throw the custom exception with validation errors
            }

            return true;
        }

        /**
         * Validates if the name is not empty or null.
         *
         * @param name the name to check
         * @return true if the name is not empty, false otherwise
         */
        private boolean isNotEmpty(String name) {
            return name != null && !name.trim().isEmpty();
        }

        /**
         * Validates if the name contains at least a first name and a surname.
         *
         * @param name the name to check
         * @return true if the name contains both a first name and a surname, false
         *         otherwise
         */
        private boolean containsFirstNameAndSurname(String name) {
            String[] parts = name.trim().split("\\s+");
            return parts.length >= 2;
        }
    }
}
