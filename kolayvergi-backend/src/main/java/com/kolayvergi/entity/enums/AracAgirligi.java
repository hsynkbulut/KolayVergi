package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AracAgirligi {
    KG_1_1500("1-1.500 kg arası"),
    KG_1501_3500("1.501-3.500 kg arası"),
    KG_3501_5000("3.501-5.000 kg arası"),
    KG_5001_10000("5.001-10.000 kg arası"),
    KG_10001_20000("10.001-20.000 kg arası"),
    KG_20001_VE_USTU("20.001 kg ve yukarısı");

    private final String value;

    AracAgirligi(String value) {
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
