package com.kolayvergi.validator.annotation;

import com.kolayvergi.validator.TCKNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TCKNValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTCKN {
    String message() default "Geçersiz TCKN formatı";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 