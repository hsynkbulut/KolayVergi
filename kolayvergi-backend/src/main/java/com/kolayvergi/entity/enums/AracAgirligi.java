package com.kolayvergi.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum AracAgirligi {
    _0_1500("1-1.500 kg arası"),
    _1501_3500("1.501-3.500 kg arası"),
    _3501_5000("3.501-5.000 kg arası"),
    _5001_10000("5.001-10.000 kg arası"),
    _10001_20000("10.001-20.000 kg arası"),
    _20001_USTU("20.001 kg ve yukarısı");

    private final String label;

    AracAgirligi(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
