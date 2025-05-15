package com.kolayvergi.validator.annotation;

import com.kolayvergi.validator.VKNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VKNValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVKN {
    String message() default "Geçersiz VKN formatı";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 