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
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class KdvVergisiHesaplamaStrategy implements VergiHesaplamaStrategy {
    private final MessageSource messageSource;

    @Override
    public Vergi hesapla(Alisveris alisveris, Kullanici kullanici, Vergi... oncekiVergiler) {
        BigDecimal matrah = alisveris.getTutar();
        UrunTuru urunTuru = alisveris.getUrunTuru();

        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (alisveris.getUrunTuru() == UrunTuru.OTOMOBIL && ObjectUtils.isEmpty(aracBilgisi)) {
            throw new IllegalArgumentException(messageSource.getMessage("vergi.kdv_arac_bilgisi_gerekli", null, LocaleContextHolder.getLocale()));
        }

        BigDecimal tabanTutar = matrah;
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
            default -> throw new IllegalArgumentException(
                    messageSource.getMessage("vergi.kdv_orani_tanimli_degil", new Object[]{urunTuru}, LocaleContextHolder.getLocale())
            );
        };
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutOran, Kullanici kullanici) {
        BigDecimal yeniOran = mevcutOran;

        if (ObjectUtils.isNotEmpty(kullanici.getYas()) && kullanici.getYas() < 25) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(2));
        }

        if (ObjectUtils.isNotEmpty(kullanici.getMeslek()) && kullanici.getMeslek().name().equals("OGRETMEN")) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(3));
        }

        if (ObjectUtils.isNotEmpty(kullanici.getCinsiyet()) &&
            kullanici.getCinsiyet().name().equals("KADIN") &&
            ObjectUtils.isNotEmpty(kullanici.getYas()) &&
            kullanici.getYas() > 40) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(1));
        }

        if (yeniOran.compareTo(BigDecimal.ZERO) < 0) {
            yeniOran = BigDecimal.ZERO;
        }

        return yeniOran;
    }
} 