package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidVKN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class VKNValidator implements ConstraintValidator<ValidVKN, String> {

    @Override
    public boolean isValid(String vkn, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(vkn)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN boş olamaz").addConstraintViolation();
            return false;
        }

        if (vkn.length() != 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN 10 haneli olmalı").addConstraintViolation();
            return false;
        }

        if (!vkn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN sadece rakamlardan oluşmalı").addConstraintViolation();
            return false;
        }

        if (vkn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("VKN sıfır ile başlayamaz").addConstraintViolation();
            return false;
        }

        return true;
    }
} 