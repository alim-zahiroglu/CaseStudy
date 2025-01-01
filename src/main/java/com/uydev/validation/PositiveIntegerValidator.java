package com.uydev.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveIntegerValidator implements ConstraintValidator<PositiveInteger, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false; // Null or empty string is invalid
        }

        try {
            int number = Integer.parseInt(value);
            return number > 0; // Valid only if it's a positive integer
        } catch (NumberFormatException e) {
            return false; // Invalid if it cannot be parsed as an integer
        }
    }
}
