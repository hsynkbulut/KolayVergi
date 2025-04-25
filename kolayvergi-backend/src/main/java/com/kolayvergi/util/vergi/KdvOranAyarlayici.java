package com.kolayvergi.util.vergi;

import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;

import java.math.BigDecimal;

public class KdvOranAyarlayici {

    public static BigDecimal uygula(BigDecimal oran, Kullanici kullanici) {
        BigDecimal sonuc = oran;

        if (kullanici.getCinsiyet().equals(Cinsiyet.KADIN.name())) {
            sonuc = sonuc.subtract(new BigDecimal("0.01"));
        }

        if (kullanici.getYas() < 25) {
            sonuc = sonuc.subtract(new BigDecimal("0.02"));
        } else if (kullanici.getYas() > 65) {
            sonuc = sonuc.subtract(new BigDecimal("0.015"));
        }

        Meslek meslek = Meslek.valueOf(kullanici.getMeslek());

        switch (meslek) {
            case MEMUR -> sonuc = sonuc.subtract(new BigDecimal("0.03"));
            case AKADEMISYEN -> sonuc = sonuc.subtract(new BigDecimal("0.02"));
            case DOKTOR, MUHENDIS -> sonuc = sonuc.subtract(new BigDecimal("0.01"));
            case OGRETMEN -> sonuc = sonuc.subtract(new BigDecimal("0.015"));
            case POLIS -> sonuc = sonuc.subtract(new BigDecimal("0.035"));
            case SERBEST_CALISAN -> sonuc = sonuc.subtract(new BigDecimal("0.022"));
            case ISADAMI -> sonuc = sonuc.subtract(new BigDecimal("0.06"));
            default -> {}
        }

        if (sonuc.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }

        return sonuc;
    }
}
