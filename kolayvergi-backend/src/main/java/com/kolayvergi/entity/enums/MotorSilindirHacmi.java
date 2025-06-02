package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MotorSilindirHacmi {
    CM3_0_1300("0-1300 cm³"),
    CM3_1301_1600("1301-1600 cm³"),
    CM3_1601_1800("1601-1800 cm³"),
    CM3_1801_2000("1801-2000 cm³"),
    CM3_2001_2500("2001-2500 cm³"),
    CM3_2501_3000("2501-3000 cm³"),
    CM3_3001_3500("3001-3500 cm³"),
    CM3_3501_4000("3501-4000 cm³"),
    CM3_4001_VE_USTU("4001+ cm³");

    private final String value;

    MotorSilindirHacmi(String value) {
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
