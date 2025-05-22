package com.kolayvergi.hesaplayici;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class KdvVergisiHesaplayici {

    public KdvVergisi hesapla(Alisveris alisveris, Kullanici kullanici, OtvVergisi otvVergisi) {
        BigDecimal matrah = alisveris.getTutar();
        UrunTuru urunTuru = alisveris.getUrunTuru();

        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalArgumentException("KDV Vergisi hesaplanabilmesi için araç bilgisi bulunmalıdır.");
        }

        BigDecimal tabanTutar;
        if (otvVergisi != null) {
            // Araç veya ÖTV uygulanmış ürün: ÖTV sonrası tutar üzerinden KDV
            tabanTutar = matrah.add(otvVergisi.getTutar());
        } else {
            // ÖTV yoksa direkt matrah üzerinden
            tabanTutar = matrah;
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
