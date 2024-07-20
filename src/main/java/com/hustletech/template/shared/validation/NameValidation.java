package com.hustletech.template.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NameValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {
    String message() default "Name must contain a first name and a surname.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NameValidation, String> {
        @Override
        public boolean isValid(String name, ConstraintValidatorContext context) {
            List<Predicate<String>> validations = Arrays.asList(
                    Validator::isNotEmpty,
                    Validator::containsFirstNameAndSurname);

            return validations.stream().allMatch(validation -> validation.test(name));
        }

        /**
         * Validates if the name is not empty or null.
         *
         * @param name the name to validate
         * @return true if the name is not empty or null, false otherwise
         */
        public static boolean isNotEmpty(String name) {
            return name != null && !name.trim().isEmpty();
        }

        /**
         * Validates if the name contains at least a first name and a surname.
         *
         * @param name the name to validate
         * @return true if the name contains at least a first name and a surname, false
         *         otherwise
         */
        public static boolean containsFirstNameAndSurname(String name) {
            String[] parts = name.trim().split("\\s+");
            return parts.length >= 2;
        }
    }
}