package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidVKN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VKNValidator implements ConstraintValidator<ValidVKN, String> {

    private final MessageSource messageSource;

    @Override
    public boolean isValid(String vkn, ConstraintValidatorContext context) {
        if (vkn == null || vkn.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.vkn_bos_olamaz", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (vkn.length() != 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.vkn_10_haneli_olmali", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (!vkn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.vkn_sadece_rakam", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (vkn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.vkn_sifir_ile_baslayamaz", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
} 