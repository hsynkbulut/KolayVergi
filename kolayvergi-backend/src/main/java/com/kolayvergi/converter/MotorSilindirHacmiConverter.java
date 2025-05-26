package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.MotorSilindirHacmi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MotorSilindirHacmiConverter implements AttributeConverter<MotorSilindirHacmi, String> {

    @Override
    public String convertToDatabaseColumn(MotorSilindirHacmi attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public MotorSilindirHacmi convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (MotorSilindirHacmi value : MotorSilindirHacmi.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz motor silindir hacmi: " + dbData);
    }
} 