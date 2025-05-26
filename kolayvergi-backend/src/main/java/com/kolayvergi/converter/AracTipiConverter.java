package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.AracTipi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AracTipiConverter implements AttributeConverter<AracTipi, String> {

    @Override
    public String convertToDatabaseColumn(AracTipi attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public AracTipi convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (AracTipi value : AracTipi.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz araç tipi: " + dbData);
    }
} 