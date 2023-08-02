package com.api.rinhadebackend.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ArrayStringLengthValidator implements ConstraintValidator<ValidArrayStringLength, List<String>> {

    @Override
    public void initialize(ValidArrayStringLength constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> array, ConstraintValidatorContext context) {
        if (array == null) {
            return true;
        }

        for (String str : array) {
            if (str == null || str.length() > 32) {
                return false;
            }
        }

        return true;
    }
}

