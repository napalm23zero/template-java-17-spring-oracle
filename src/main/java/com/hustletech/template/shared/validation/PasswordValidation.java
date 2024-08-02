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

@Documented
@Constraint(validatedBy = PasswordValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Password must be strong.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<PasswordValidation, String> {
        private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        @Override
        public boolean isValid(String password, ConstraintValidatorContext context) {
            List<String> errors = new ArrayList<>();

            if (password == null || password.isEmpty()) {
                errors.add("Password cannot be empty.");
            } else {
                if (!password.matches(PASSWORD_PATTERN)) {
                    errors.addAll(analyzePasswordStrength(password));
                }
            }

            if (!errors.isEmpty()) {
                throw new BadRequestException(errors);
            }

            return true;
        }

        /**
         * Analyzes the password and returns specific weaknesses found in it.
         *
         * @param password The password to analyze
         * @return A list of issues found in the password
         */
        private List<String> analyzePasswordStrength(String password) {
            List<String> issues = new ArrayList<>();
            if (!password.matches(".*[0-9].*")) {
                issues.add("Password must contain at least one digit.");
            }
            if (!password.matches(".*[a-z].*")) {
                issues.add("Password must contain at least one lowercase letter.");
            }
            if (!password.matches(".*[A-Z].*")) {
                issues.add("Password must contain at least one uppercase letter.");
            }
            if (!password.matches(".*[@#$%^&+=].*")) {
                issues.add("Password must contain at least one special character (@#$%^&+=).");
            }
            if (!password.matches(".{8,}")) {
                issues.add("Password must be at least 8 characters long.");
            }
            if (password.matches(".*\\s+.*")) {
                issues.add("Password must not contain any spaces.");
            }
            return issues;
        }
    }
}
