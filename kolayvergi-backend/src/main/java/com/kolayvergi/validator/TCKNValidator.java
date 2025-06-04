package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidTCKN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class TCKNValidator implements ConstraintValidator<ValidTCKN, String> {

    private final MessageSource messageSource;

    @Override
    public boolean isValid(String tckn, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(tckn)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.tckn_bos_olamaz", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (tckn.length() != 11) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.tckn_11_haneli_olmali", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (!tckn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.tckn_sadece_rakam", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        if (tckn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.tckn_sifir_ile_baslayamaz", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        int[] digits = tckn.chars().map(Character::getNumericValue).toArray();

        // 1, 3, 5, 7, 9. hanelerin toplamının 7 katından, 2, 4, 6, 8. hanelerin toplamı çıkartıldığında,
        // elde edilen sonucun 10'a bölümünden kalan, yani Mod10'u bize 10. haneyi verir.
        int oddSum = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int evenSum = digits[1] + digits[3] + digits[5] + digits[7];
        int digit10 = ((oddSum * 7) - evenSum) % 10;

        // 1. 2. 3. 4. 5. 6. 7. 8. 9. 10. hanelerin toplamından elde edilen sonucun
        // 10'a bölümünden kalan, yani Mod10'u bize 11. haneyi verir.
        int sum = digits[0] + digits[1] + digits[2] + digits[3] + digits[4] + 
                 digits[5] + digits[6] + digits[7] + digits[8] + digit10;
        int digit11 = sum % 10;

        if (digit10 != digits[9] || digit11 != digits[10]) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("validation.tckn_gecersiz_format", null, LocaleContextHolder.getLocale())
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
} 