package com.kolayvergi.strategy.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.strategy.VergiHesaplayiciStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("KDV")
@RequiredArgsConstructor
public class KdvVergisiHesaplayici implements VergiHesaplayiciStrategy {

    @Override
    public BigDecimal hesapla(BigDecimal alisverisTutari, Kullanici kullanici) {
        Alisveris alisveris = kullanici.getAlisverisler()
                .stream()
                .filter(a -> a.getUrunTuru() != null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Alışveriş bilgisi bulunamadı."));

        BigDecimal oran = new BigDecimal("0.18"); // Default KDV oranı %18

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN && kullanici.getYas() < 30) {
            oran = oran.subtract(new BigDecimal("0.02")); // Genç kadınlara KDV indirimi
        } else if (kullanici.getYas() < 25) {
            oran = oran.subtract(new BigDecimal("0.015")); // Gençlere genel indirim
        } else if (kullanici.getYas() > 65) {
            oran = oran.subtract(new BigDecimal("0.01")); // 65 yaş üstü küçük indirim
        }

        switch (kullanici.getMeslek()) {
            case AKADEMISYEN, OGRETMEN -> oran = oran.subtract(new BigDecimal("0.05")); // Eğitimcilere büyük indirim
            case POLIS, DOKTOR -> oran = oran.subtract(new BigDecimal("0.03")); // Kamu güvenliği ve sağlık sektörüne indirim
            case MEMUR, MUHENDIS -> oran = oran.subtract(new BigDecimal("0.01"));
            case SERBEST_CALISAN, ISADAMI -> oran = oran.add(new BigDecimal("0.02")); // İşadamlarına/s.erbest çalışanlara artış
        }

        if (kullanici.getMaas().compareTo(new BigDecimal("70000")) > 0) {
            oran = oran.add(new BigDecimal("0.02")); // Yüksek maaşlı kişilere ek KDV
        } else if (kullanici.getMaas().compareTo(new BigDecimal("20000")) < 0) {
            oran = oran.subtract(new BigDecimal("0.015")); // Düşük gelirlilere indirim
        }

        switch (alisveris.getUrunTuru()) {
            case GIDA -> oran = new BigDecimal("0.08"); // Gıdada düşük KDV
            case KITAP -> oran = new BigDecimal("0.01"); // Kitapta sembolik KDV
            case ELEKTRONIK -> oran = new BigDecimal("0.20"); // Elektronikte yüksek KDV
            case GIYIM -> oran = new BigDecimal("0.18"); // Giyim standart
            case KOZMETIK -> oran = new BigDecimal("0.25"); // Kozmetikte yüksek KDV
            case MOBILYA -> oran = new BigDecimal("0.15"); // Mobilyada orta seviye
            case BEYAZ_ESYA -> oran = new BigDecimal("0.18"); // Beyaz eşya standart
            case OTOMOBIL -> oran = new BigDecimal("0.18"); // Otomobilde de KDV var
        }

        return alisverisTutari.multiply(oran).setScale(2, RoundingMode.HALF_UP);
    }
}

