package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.AracYasi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AracYasiConverter implements AttributeConverter<AracYasi, String> {

    @Override
    public String convertToDatabaseColumn(AracYasi attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public AracYasi convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (AracYasi value : AracYasi.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz araç yaşı: " + dbData);
    }
} 