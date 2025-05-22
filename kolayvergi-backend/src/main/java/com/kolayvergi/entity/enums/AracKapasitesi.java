package com.kolayvergi.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum AracKapasitesi {
    _1_25("25 kişiye kadar"),
    _26_35("26-35 kişiye kadar"),
    _36_45("36-45 kişiye kadar"),
    _46_USTU("46 kişi ve yukarısı");

    private final String label;

    AracKapasitesi(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
