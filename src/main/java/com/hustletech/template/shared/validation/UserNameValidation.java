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
@Constraint(validatedBy = UserNameValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameValidation {
    String message() default "Invalid username.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validator class to enforce username constraints, ensuring usernames meet
     * specified formatting rules.
     */
    class Validator implements ConstraintValidator<UserNameValidation, String> {
        private static final String USERNAME_PATTERN = "^(?!\\.)(?!.*\\.$)[a-zA-Z0-9_.-]{3,16}$";

        @Override
        public boolean isValid(String username, ConstraintValidatorContext context) {
            List<String> errors = new ArrayList<>();

            if (username == null || username.isEmpty()) {
                errors.add("Username cannot be empty.");
            } else {
                if (!username.matches(USERNAME_PATTERN)) {
                    errors.addAll(analyzeUsername(username));
                }
            }

            if (!errors.isEmpty()) {
                throw new BadRequestException(errors);
            }

            return true;
        }

        /**
         * Analyzes the username and returns specific issues found in it, based on
         * expected formatting rules.
         *
         * @param username The username to analyze
         * @return A list of issues found in the username
         */
        private List<String> analyzeUsername(String username) {
            List<String> issues = new ArrayList<>();
            if (!username.matches("^[a-zA-Z0-9_.-]+$")) {
                issues.add("Username must contain only letters, numbers, underscores, dashes, or dots.");
            }
            if (username.length() < 3 || username.length() > 16) {
                issues.add("Username must be between 3 and 16 characters long.");
            }
            if (username.startsWith(".") || username.endsWith(".")) {
                issues.add("Username cannot start or end with a dot.");
            }
            return issues;
        }
    }
}
