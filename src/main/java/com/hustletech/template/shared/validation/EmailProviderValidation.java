package com.hustletech.template.shared.validation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.hustletech.template.shared.exception.BadRequestException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate email addresses based on specific provider
 * constraints.
 */
public @interface EmailProviderValidation {
    String message() default "Invalid email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validator class implementing constraint validation for
     * EmailProviderValidation.
     */
    class Validator implements ConstraintValidator<EmailProviderValidation, String> {
        private static final String[] validProviders = { "gmail.com", "yahoo.com", "outlook.com" };

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            List<String> errors = new LinkedList<>();

            if (email == null || email.trim().isEmpty()) {
                errors.add("Email cannot be empty.");
            }

            if (email != null && (!email.contains("@") || !email.split("@")[1].contains("."))) {
                errors.add("Email must contain a '@' symbol and a valid domain.");
            }

            // if (!isValidProvider(email)) {
            // errors.add("Email must be from a valid provider (e.g., " +
            // validProviders.toString() + ").");
            // }

            if (!errors.isEmpty()) {
                throw new BadRequestException(errors); // Throw the custom exception with validation errors
            }

            return true;
        }

        /**
         * Validates if the email's domain is from a valid provider.
         *
         * @param email the email to check
         * @return true if the email domain is in the list of valid providers, false
         *         otherwise
         */
        @SuppressWarnings("unused")
        private boolean isValidProvider(String email) {
            if (email.contains("@")) {
                String domain = email.substring(email.indexOf('@') + 1);
                return Arrays.stream(validProviders).anyMatch(domain::equals);
            }
            return false;
        }
    }
}
