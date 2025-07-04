package com.kolayvergi.validator;

import com.kolayvergi.validator.annotation.ValidTCKN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class TCKNValidator implements ConstraintValidator<ValidTCKN, String> {

    @Override
    public boolean isValid(String tckn, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(tckn)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("TCKN boş olamaz").addConstraintViolation();
            return false;
        }

        if (tckn.length() != 11) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("TCKN 11 haneli olmalı").addConstraintViolation();
            return false;
        }

        if (!tckn.matches("\\d+")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("TCKN sadece rakamlardan oluşmalı").addConstraintViolation();
            return false;
        }

        if (tckn.charAt(0) == '0') {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("TCKN sıfır ile başlayamaz").addConstraintViolation();
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
            context.buildConstraintViolationWithTemplate("Geçersiz TCKN formatı").addConstraintViolation();
            return false;
        }

        return true;
    }
} 