package com.kolayvergi.entity.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum UrunTuru {
    OTOMOBIL(new BigDecimal("0.18")),
    GIDA(new BigDecimal("0.08")),
    ELEKTRONIK(new BigDecimal("0.18")),
    GIYIM(new BigDecimal("0.18")),
    KITAP(new BigDecimal("0.01")),
    KOZMETIK(new BigDecimal("0.20")),
    MOBILYA(new BigDecimal("0.18")),
    BEYAZ_ESYA(new BigDecimal("0.18")),
    INTERNET_HIZMETI(new BigDecimal("0.10")),
    ILAC(new BigDecimal("0.05"));

    private final BigDecimal oran;

    UrunTuru(BigDecimal oran) {
        this.oran = oran;
    }

    public BigDecimal getOran() {
        return oran;
    }
}