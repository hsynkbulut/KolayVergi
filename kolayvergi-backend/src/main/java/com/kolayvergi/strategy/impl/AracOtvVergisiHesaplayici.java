package com.kolayvergi.strategy.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.strategy.VergiHesaplayiciStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("OTV")
@RequiredArgsConstructor
public class AracOtvVergisiHesaplayici implements VergiHesaplayiciStrategy {

    @Override
    public BigDecimal hesapla(BigDecimal alisverisTutari, Kullanici kullanici) {
        AracBilgisi aracBilgisi = kullanici.getAlisverisler()
                .stream()
                .filter(alisveris -> alisveris.getUrunTuru() == UrunTuru.OTOMOBIL)
                .findFirst()
                .map(Alisveris::getAracBilgisi)
                .orElse(null);

        if (aracBilgisi == null) {
            throw new IllegalStateException("Araç bilgisi bulunamadı.");
        }

        BigDecimal oran = new BigDecimal("0.45"); // Temel ÖTV oranı %45

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN && kullanici.getYas() < 30) {
            oran = oran.subtract(new BigDecimal("0.07")); // Genç kadınlara %7 indirim
        } else if (kullanici.getCinsiyet() == Cinsiyet.ERKEK && kullanici.getYas() < 25) {
            oran = oran.subtract(new BigDecimal("0.05")); // Genç erkeklere %5 indirim
        }

        switch (kullanici.getMeslek()) {
            case AKADEMISYEN -> oran = oran.subtract(new BigDecimal("0.03"));
            case OGRETMEN -> oran = oran.subtract(new BigDecimal("0.04"));
            case POLIS, DOKTOR -> oran = oran.subtract(new BigDecimal("0.02"));
            case ISADAMI, SERBEST_CALISAN -> oran = oran.add(new BigDecimal("0.02"));
            case MEMUR, MUHENDIS -> {
            }
        }

        if (kullanici.getMaas().compareTo(new BigDecimal("75000")) > 0) {
            oran = oran.add(new BigDecimal("0.03")); // Yüksek gelirliye %3 fazla vergi
        } else if (kullanici.getMaas().compareTo(new BigDecimal("20000")) < 0) {
            oran = oran.subtract(new BigDecimal("0.02")); // Düşük gelirliden %2 az
        }

        switch (aracBilgisi.getAracTipi().toUpperCase()) {
            case "SUV" -> oran = oran.add(new BigDecimal("0.05"));
            case "HATCHBACK" -> oran = oran.subtract(new BigDecimal("0.01"));
            case "SEDAN" -> {
            }
            case "MINIVAN", "PICKUP" -> oran = oran.add(new BigDecimal("0.04"));
        }

        Integer motorHacmi = aracBilgisi.getMotorSilindirHacmi();
        if (motorHacmi != null) {
            if (motorHacmi > 3000) {
                oran = oran.add(new BigDecimal("0.10")); // Çok büyük motorlarda %10 ek
            } else if (motorHacmi > 2000) {
                oran = oran.add(new BigDecimal("0.07"));
            } else if (motorHacmi < 1200) {
                oran = oran.subtract(new BigDecimal("0.02")); // Küçük motorlarda %2 indirim
            }
        }

        String marka = aracBilgisi.getMarka().toUpperCase();
        if (marka.contains("MERCEDES") || marka.contains("BMW") || marka.contains("AUDI")) {
            oran = oran.add(new BigDecimal("0.04")); // Lüks markalara ek vergi
        } else if (marka.contains("FIAT") || marka.contains("RENAULT")) {
            oran = oran.subtract(new BigDecimal("0.01")); // Uygun fiyatlı markalarda indirim
        }

        return alisverisTutari.multiply(oran).setScale(2, RoundingMode.HALF_UP);
    }
}

