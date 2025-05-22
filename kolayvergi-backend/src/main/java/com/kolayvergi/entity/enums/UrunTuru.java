package com.kolayvergi.entity.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum UrunTuru {
    OTOMOBIL(new BigDecimal("1.00")),
    GIDA(new BigDecimal("0.00")),
    ELEKTRONIK(new BigDecimal("0.80")),
    GIYIM(new BigDecimal("0.30")),
    KITAP(new BigDecimal("0.00")),
    KOZMETIK(new BigDecimal("0.60")),
    MOBILYA(new BigDecimal("0.50")),
    BEYAZ_ESYA(new BigDecimal("0.40"));

    private final BigDecimal luksKatSayisi;

    UrunTuru(BigDecimal luksKatSayisi) {
        this.luksKatSayisi = luksKatSayisi;
    }
}
