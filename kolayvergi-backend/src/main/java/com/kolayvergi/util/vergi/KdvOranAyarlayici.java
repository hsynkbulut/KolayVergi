package com.kolayvergi.util.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import com.kolayvergi.entity.enums.UrunTuru;

import java.math.BigDecimal;

public class KdvOranAyarlayici {

    public static BigDecimal uygula(Kullanici kullanici, Alisveris alisveris) {
        BigDecimal oran = getUrunTuruOrani(alisveris.getUrunTuru());

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN) {
            oran = oran.subtract(new BigDecimal("0.01"));
        }

        if (kullanici.getYas() < 25) {
            oran = oran.subtract(new BigDecimal("0.02"));
        } else if (kullanici.getYas() > 65) {
            oran = oran.subtract(new BigDecimal("0.015"));
        }

        Meslek meslek = kullanici.getMeslek();

        switch (meslek) {
            case MEMUR -> oran = oran.subtract(new BigDecimal("0.03"));
            case AKADEMISYEN -> oran = oran.subtract(new BigDecimal("0.02"));
            case DOKTOR, MUHENDIS -> oran = oran.subtract(new BigDecimal("0.01"));
            case OGRETMEN -> oran = oran.subtract(new BigDecimal("0.015"));
            case POLIS -> oran = oran.subtract(new BigDecimal("0.035"));
            case SERBEST_CALISAN -> oran = oran.subtract(new BigDecimal("0.022"));
            case ISADAMI -> oran = oran.subtract(new BigDecimal("0.06"));
            default -> {}
        }

        return oran.max(BigDecimal.ZERO);
    }

    private static BigDecimal getUrunTuruOrani(UrunTuru urunTuru) {
        return switch (urunTuru) {
            case OTOMOBIL, ELEKTRONIK, GIYIM, MOBILYA, BEYAZ_ESYA -> new BigDecimal("0.18");
            case GIDA -> new BigDecimal("0.08");
            case KOZMETIK -> new BigDecimal("0.20");
            case KITAP -> new BigDecimal("0.01");
        };
    }
}
