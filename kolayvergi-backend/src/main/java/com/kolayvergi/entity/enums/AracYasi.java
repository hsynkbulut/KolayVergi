package com.kolayvergi.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum AracYasi {
    BIR_UC("1-3 yaş"),
    DORT_ALTI("4-6 yaş"),
    YEDI_ONBIR("7-11 yaş"),
    ONIKI_ONBES("12-15 yaş"),
    ONALTI_USTU("16+ yaş");

    private final String label;

    AracYasi(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
