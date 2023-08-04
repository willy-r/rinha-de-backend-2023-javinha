package com.api.rinhadebackend.dtos.pessoa.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ArrayStringLengthValidator.class)
public @interface ValidArrayStringLength {
    String message() default "Each string in the array must have a maximum length of 32 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
