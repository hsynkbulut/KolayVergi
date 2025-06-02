package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AracTipi {
    OTOMOBIL_KAPTIKACTI_ARAZI("Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri"),
    MINIBUS("Minibüsler"),
    OTOBUS("Otobüs ve benzerleri"),
    KAMYON_KAMYONET_CEKICI("Kamyon-Kamyonet-Çekici ve benzerleri");

    private final String value;

    AracTipi(String value) {
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