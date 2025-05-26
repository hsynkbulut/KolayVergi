package com.kolayvergi.strategy.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.strategy.VergiHesaplamaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class KdvVergisiHesaplamaStrategy implements VergiHesaplamaStrategy {

    @Override
    public Vergi hesapla(Alisveris alisveris, Kullanici kullanici, Vergi... oncekiVergiler) {
        BigDecimal matrah = alisveris.getTutar();
        UrunTuru urunTuru = alisveris.getUrunTuru();

        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalArgumentException("KDV Vergisi hesaplanabilmesi için araç bilgisi bulunmalıdır.");
        }

        BigDecimal tabanTutar = matrah;
        // ÖTV varsa, KDV'yi ÖTV sonrası tutar üzerinden hesapla
        for (Vergi oncekiVergi : oncekiVergiler) {
            if (oncekiVergi instanceof OtvVergisi) {
                tabanTutar = tabanTutar.add(oncekiVergi.getTutar());
                break;
            }
        }

        BigDecimal kdvOrani = getUrunTuruKdvOrani(urunTuru);
        kdvOrani = uygulaKullaniciIndirimleri(kdvOrani, kullanici);

        BigDecimal kdvTutari = tabanTutar
                .multiply(kdvOrani)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        KdvVergisi kdvVergisi = new KdvVergisi();
        kdvVergisi.setAlisveris(alisveris);
        kdvVergisi.setAracBilgisi(aracBilgisi);
        kdvVergisi.setMatrah(tabanTutar);
        kdvVergisi.setOran(kdvOrani);
        kdvVergisi.setTutar(kdvTutari);
        kdvVergisi.setUrunTuru(urunTuru);

        return kdvVergisi;
    }

    private BigDecimal getUrunTuruKdvOrani(UrunTuru urunTuru) {
        return switch (urunTuru) {
            case GIDA -> BigDecimal.valueOf(8);
            case ELEKTRONIK, GIYIM, BEYAZ_ESYA -> BigDecimal.valueOf(20);
            case KITAP -> BigDecimal.valueOf(5);
            case KOZMETIK -> BigDecimal.valueOf(20);
            case MOBILYA -> BigDecimal.valueOf(20);
            case OTOMOBIL -> BigDecimal.valueOf(20);
            default -> throw new IllegalArgumentException("Ürün türü için KDV oranı tanımlı değil: " + urunTuru);
        };
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutOran, Kullanici kullanici) {
        BigDecimal yeniOran = mevcutOran;

        if (kullanici.getYas() != null && kullanici.getYas() < 25) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(2));
        }

        if (kullanici.getMeslek() != null && kullanici.getMeslek().name().equals("OGRETMEN")) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(3));
        }

        if (kullanici.getCinsiyet() != null &&
                kullanici.getCinsiyet().name().equals("KADIN") &&
                kullanici.getYas() != null &&
                kullanici.getYas() > 40) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(1));
        }

        if (yeniOran.compareTo(BigDecimal.ZERO) < 0) {
            yeniOran = BigDecimal.ZERO;
        }

        return yeniOran;
    }
} 