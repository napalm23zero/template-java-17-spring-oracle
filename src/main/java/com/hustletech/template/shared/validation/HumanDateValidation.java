
package com.hustletech.template.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = HumanDateValidation.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HumanDateValidation {
    String message() default "Birthday must be a humanly possible date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<HumanDateValidation, LocalDate> {
        @Override
        public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
            List<Predicate<LocalDate>> validations = Arrays.asList(
                    Validator::isNotNull,
                    Validator::isWithinHumanRange);

            return validations.stream().allMatch(validation -> validation.test(birthday));
        }

        /**
         * Validates if the birthday is not null.
         *
         * @param birthday the birthday to validate
         * @return true if the birthday is not null, false otherwise
         */
        public static boolean isNotNull(LocalDate birthday) {
            return birthday != null;
        }

        /**
         * Validates if the birthday is within a humanly possible range.
         *
         * @param birthday the birthday to validate
         * @return true if the birthday is within a humanly possible range, false
         *         otherwise
         */
        public static boolean isWithinHumanRange(LocalDate birthday) {
            LocalDate today = LocalDate.now();
            LocalDate veryOldHuman = today.minusYears(120);
            LocalDate veryYoungHuman = today.minusYears(1);
            return birthday.isAfter(veryOldHuman) && birthday.isBefore(veryYoungHuman);
        }
    }
}