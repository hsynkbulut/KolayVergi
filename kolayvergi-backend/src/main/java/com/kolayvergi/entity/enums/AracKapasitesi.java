package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AracKapasitesi {
    KISI_1_25("25 kişiye kadar"),
    KISI_26_35("26-35 kişiye kadar"),
    KISI_36_45("36-45 kişiye kadar"),
    KISI_46_VE_USTU("46 kişi ve yukarısı");

    private final String value;

    AracKapasitesi(String value) {
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
