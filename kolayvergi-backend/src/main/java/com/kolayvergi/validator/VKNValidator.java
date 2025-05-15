package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidVKN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VKNValidator implements ConstraintValidator<ValidVKN, String> {

    @Override
    public boolean isValid(String vkn, ConstraintValidatorContext context) {
        if (vkn == null || vkn.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN boş bırakılamaz.")
                   .addConstraintViolation();
            return false;
        }

        // VKN 10 haneli olmalı
        if (vkn.length() != 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN 10 haneli olmalıdır.")
                   .addConstraintViolation();
            return false;
        }

        // Sadece rakam içermeli
        if (!vkn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN sadece rakamlardan oluşmalıdır.")
                   .addConstraintViolation();
            return false;
        }

        // İlk rakam 0 olamaz
        if (vkn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN 0 ile başlayamaz.")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
} 