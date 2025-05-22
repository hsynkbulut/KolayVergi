package com.kolayvergi.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AracTipi {
    OTOMOBIL_KAPTIKACTI_ARAZI("Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri"),
    MINIBUS("Minibüsler"),
    OTOBUS("Otobüs ve benzerleri"),
    KAMYON_KAMYONET_CEKICI("Kamyon-Kamyonet-Çekici ve benzerleri");

    private final String label;

    AracTipi(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}