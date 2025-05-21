package com.kolayvergi.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum MotorSilindirHacmi {
    SIFIR_1300("0-1300 cm³"),
    _1301_1600("1301-1600 cm³"),
    _1601_1800("1601-1800 cm³"),
    _1801_2000("1801-2000 cm³"),
    _2001_2500("2001-2500 cm³"),
    _2501_3000("2501-3000 cm³"),
    _3001_3500("3001-3500 cm³"),
    _3501_4000("3501-4000 cm³"),
    _4001_USTU("4001+ cm³");

    private final String label;

    MotorSilindirHacmi(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
