package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidVKN;
import com.kolayvergi.constant.ValidationConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VKNValidator implements ConstraintValidator<ValidVKN, String> {

    @Override
    public boolean isValid(String vkn, ConstraintValidatorContext context) {
        if (vkn == null || vkn.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationConstants.VKN_BOS_OLAMAZ)
                   .addConstraintViolation();
            return false;
        }

        if (vkn.length() != 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationConstants.VKN_10_HANELI_OLMALI)
                   .addConstraintViolation();
            return false;
        }

        if (!vkn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationConstants.VKN_SADECE_RAKAM)
                   .addConstraintViolation();
            return false;
        }

        if (vkn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationConstants.VKN_SIFIR_ILE_BASLAYAMAZ)
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
} 