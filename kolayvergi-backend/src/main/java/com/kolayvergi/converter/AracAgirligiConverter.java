package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.AracAgirligi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AracAgirligiConverter implements AttributeConverter<AracAgirligi, String> {

    @Override
    public String convertToDatabaseColumn(AracAgirligi attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public AracAgirligi convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (AracAgirligi value : AracAgirligi.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz araç ağırlığı: " + dbData);
    }
} 