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
@Constraint(validatedBy = EmailProviderValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailProviderValidation {
    String message() default "Email must be from a valid provider.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<EmailProviderValidation, String> {
        private static final String[] validProviders = { "gmail.com", "yahoo.com", "outlook.com" };

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            List<Predicate<String>> validations = Arrays.asList(
                    Validator::isNotEmpty,
                    Validator::hasValidFormat
            // Validator::isValidProvider
            );

            return validations.stream().allMatch(validation -> validation.test(email));
        }

        /**
         * Validates if the email is not empty or null.
         *
         * @param email the email to validate
         * @return true if the email is not empty or null, false otherwise
         */
        public static boolean isNotEmpty(String email) {
            return email != null && !email.trim().isEmpty();
        }

        /**
         * Validates if the email has a valid format.
         *
         * @param email the email to validate
         * @return true if the email has a valid format, false otherwise
         */
        public static boolean hasValidFormat(String email) {
            String[] parts = email.split("@");
            return parts.length == 2;
        }

        /**
         * Validates if the email is from a valid provider.
         *
         * @param email the email to validate
         * @return true if the email is from a valid provider, false otherwise
         */
        public static boolean isValidProvider(String email) {
            String domain = email.split("@")[1];
            for (String provider : validProviders) {
                if (domain.equals(provider)) {
                    return true;
                }
            }
            return false;
        }
    }
}