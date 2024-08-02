package com.hustletech.template.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hustletech.template.shared.exception.BadRequestException;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate if a given date is within a reasonable human
 * lifespan.
 */
@Documented
@Constraint(validatedBy = HumanDateValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HumanDateValidation {
    String message() default "Invalid Birthday.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Validator to enforce that the given LocalDate falls within a plausible human
     * lifespan.
     */
    class Validator implements ConstraintValidator<HumanDateValidation, LocalDate> {
        @Override
        public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
            List<String> errors = new ArrayList<>();

            if (!isNotNull(birthday)) {
                errors.add("Birthday must not be null.");
            }

            if (birthday != null) {
                if (!isWithinHumanRange(birthday)) {
                    errors.addAll(determineAgeIssues(birthday));
                }
            }

            if (!errors.isEmpty()) {
                throw new BadRequestException(errors);
            }

            return true;
        }

        /**
         * Validates if the birthday is not null.
         *
         * @param birthday the date to check
         * @return true if the birthday is not null, false otherwise
         */
        private boolean isNotNull(LocalDate birthday) {
            return birthday != null;
        }

        /**
         * Validates if the birthday is within a reasonable human lifespan.
         *
         * @param birthday the date to check
         * @return true if the birthday is within human lifespan limits, false otherwise
         */
        private boolean isWithinHumanRange(LocalDate birthday) {
            LocalDate today = LocalDate.now();
            return !birthday.isBefore(today.minusYears(120)) && !birthday.isAfter(today.minusYears(1));
        }

        /**
         * Determines specific age-related issues based on the birthday provided.
         *
         * @param birthday the date to check
         * @return a list of error messages concerning age-related issues
         */
        private List<String> determineAgeIssues(LocalDate birthday) {
            List<String> issues = new ArrayList<>();
            LocalDate today = LocalDate.now();
            if (birthday.isBefore(today.minusYears(120))) {
                issues.add("Birthday indicates an age greater than 120 years, which is not typically possible.");
            }
            if (birthday.isAfter(today.minusYears(1))) {
                issues.add("Birthday indicates an age less than 1 year, which is too recent.");
            }
            return issues;
        }
    }
}
