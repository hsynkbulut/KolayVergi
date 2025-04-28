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

@Component("MTV")
@RequiredArgsConstructor
public class MtvVergisiHesaplayici implements VergiHesaplayiciStrategy {

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

        BigDecimal oran = new BigDecimal("0.05"); // Temel MTV oranı %5

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN && kullanici.getYas() < 30) {
            oran = oran.subtract(new BigDecimal("0.01"));
        } else if (kullanici.getYas() > 65) {
            oran = oran.subtract(new BigDecimal("0.02")); // Yaşlı kullanıcılar için küçük indirim
        }

        switch (kullanici.getMeslek()) {
            case OGRETMEN, AKADEMISYEN -> oran = oran.subtract(new BigDecimal("0.015"));
            case POLIS, DOKTOR -> oran = oran.subtract(new BigDecimal("0.01"));
            case ISADAMI, SERBEST_CALISAN -> oran = oran.add(new BigDecimal("0.015"));
            case MEMUR, MUHENDIS -> {
                // Değişiklik yok
            }
        }

        if (kullanici.getMaas().compareTo(new BigDecimal("60000")) > 0) {
            oran = oran.add(new BigDecimal("0.02"));
        } else if (kullanici.getMaas().compareTo(new BigDecimal("20000")) < 0) {
            oran = oran.subtract(new BigDecimal("0.01"));
        }

        switch (aracBilgisi.getAracTipi().toUpperCase()) {
            case "SUV" -> oran = oran.add(new BigDecimal("0.02"));
            case "MINIVAN" -> oran = oran.add(new BigDecimal("0.015"));
            case "HATCHBACK" -> oran = oran.subtract(new BigDecimal("0.01"));
            case "SEDAN" -> {
                // Sedan normal
            }
            case "PICKUP" -> oran = oran.add(new BigDecimal("0.02"));
        }

        Integer motorHacmi = aracBilgisi.getMotorSilindirHacmi();
        if (motorHacmi != null) {
            if (motorHacmi > 3000) {
                oran = oran.add(new BigDecimal("0.06"));
            } else if (motorHacmi > 2000) {
                oran = oran.add(new BigDecimal("0.04"));
            } else if (motorHacmi < 1200) {
                oran = oran.subtract(new BigDecimal("0.015"));
            }
        }

        Integer aracYasi = aracBilgisi.getAracYasi();
        if (aracYasi != null) {
            if (aracYasi > 10) {
                oran = oran.subtract(new BigDecimal("0.02")); // 10 yaş üstü araçlara indirim
            } else if (aracYasi <= 3) {
                oran = oran.add(new BigDecimal("0.01")); // 0-3 yaş arası yeni araçlar
            }
        }

        Integer ilkTescilYili = aracBilgisi.getIlkTescilYili();
        if (ilkTescilYili != null) {
            if (ilkTescilYili < 2010) {
                oran = oran.subtract(new BigDecimal("0.015")); // Eski araçlara vergi indirimi
            } else if (ilkTescilYili >= 2020) {
                oran = oran.add(new BigDecimal("0.02")); // Yeni araçlara daha yüksek MTV
            }
        }

        return alisverisTutari.multiply(oran).setScale(2, RoundingMode.HALF_UP);
    }
}

