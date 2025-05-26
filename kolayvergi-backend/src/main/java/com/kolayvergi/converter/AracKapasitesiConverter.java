package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.AracKapasitesi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AracKapasitesiConverter implements AttributeConverter<AracKapasitesi, String> {

    @Override
    public String convertToDatabaseColumn(AracKapasitesi attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public AracKapasitesi convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (AracKapasitesi value : AracKapasitesi.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz araç kapasitesi: " + dbData);
    }
} 