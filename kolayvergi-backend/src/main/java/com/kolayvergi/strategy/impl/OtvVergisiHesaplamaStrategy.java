package com.kolayvergi.strategy.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.AracTipi;
import com.kolayvergi.entity.enums.MotorSilindirHacmi;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.strategy.VergiHesaplamaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class OtvVergisiHesaplamaStrategy implements VergiHesaplamaStrategy {

    @Override
    public Vergi hesapla(Alisveris alisveris, Kullanici kullanici, Vergi... oncekiVergiler) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        UrunTuru urunTuru = alisveris.getUrunTuru();
        BigDecimal matrah = alisveris.getTutar();
        AracTipi aracTipi = aracBilgisi != null ? aracBilgisi.getAracTipi() : null;

        BigDecimal otvOrani;

        if (aracTipi != null) {
            otvOrani = hesaplaAracOtvOrani(aracTipi, aracBilgisi, matrah);
        } else {
            // Araçsız ürünler için
            otvOrani = urunTuru.getLuksKatSayisi().multiply(BigDecimal.valueOf(100));
        }

        otvOrani = uygulaKullaniciIndirimleri(otvOrani, kullanici);

        BigDecimal otvTutari = matrah
                .multiply(otvOrani)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        OtvVergisi otvVergisi = new OtvVergisi();
        otvVergisi.setAlisveris(alisveris);
        otvVergisi.setMatrah(matrah);
        otvVergisi.setOran(otvOrani);
        otvVergisi.setTutar(otvTutari);
        otvVergisi.setAracBilgisi(aracBilgisi);
        otvVergisi.setUrunTuru(urunTuru);
        otvVergisi.setLuksUrunKatSayisi(urunTuru.getLuksKatSayisi());

        return otvVergisi;
    }

    private BigDecimal hesaplaAracOtvOrani(AracTipi tip, AracBilgisi arac, BigDecimal matrah) {
        MotorSilindirHacmi hacim = arac.getMotorSilindirHacmi();

        return switch (tip) {
            case OTOMOBIL_KAPTIKACTI_ARAZI -> hesaplaOtomobilOtvOrani(hacim, matrah);
            case MINIBUS -> BigDecimal.valueOf(9);
            case OTOBUS -> BigDecimal.valueOf(1);
            case KAMYON_KAMYONET_CEKICI -> BigDecimal.valueOf(4);
        };
    }

    private BigDecimal hesaplaOtomobilOtvOrani(MotorSilindirHacmi hacim, BigDecimal matrah) {
        switch (hacim) {
            case CM3_0_1300, CM3_1301_1600:
                if (matrah.compareTo(BigDecimal.valueOf(184_000)) <= 0) return BigDecimal.valueOf(45);
                if (matrah.compareTo(BigDecimal.valueOf(220_000)) <= 0) return BigDecimal.valueOf(50);
                if (matrah.compareTo(BigDecimal.valueOf(250_000)) <= 0) return BigDecimal.valueOf(60);
                if (matrah.compareTo(BigDecimal.valueOf(280_000)) <= 0) return BigDecimal.valueOf(70);
                return BigDecimal.valueOf(80);
            case CM3_1601_1800, CM3_1801_2000:
                if (matrah.compareTo(BigDecimal.valueOf(170_000)) <= 0) return BigDecimal.valueOf(130);
                return BigDecimal.valueOf(150);
            default:
                return BigDecimal.valueOf(220);
        }
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