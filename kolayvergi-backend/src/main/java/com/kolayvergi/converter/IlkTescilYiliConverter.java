package com.kolayvergi.converter;

import com.kolayvergi.entity.enums.IlkTescilYili;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IlkTescilYiliConverter implements AttributeConverter<IlkTescilYili, String> {

    @Override
    public String convertToDatabaseColumn(IlkTescilYili attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getLabel();
    }

    @Override
    public IlkTescilYili convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (IlkTescilYili value : IlkTescilYili.values()) {
            if (value.getLabel().equals(dbData)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Geçersiz ilk tescil yılı: " + dbData);
    }
} 