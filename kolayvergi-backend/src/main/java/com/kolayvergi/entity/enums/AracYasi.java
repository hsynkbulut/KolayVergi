package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AracYasi {
    BIR_UC_YAS("1-3 yaş"),
    DORT_ALTI_YAS("4-6 yaş"),
    YEDI_ONBIR_YAS("7-11 yaş"),
    ONIKI_ONBES_YAS("12-15 yaş"),
    ONALTI_USTU_YAS("16+ yaş");

    private final String value;

    AracYasi(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
