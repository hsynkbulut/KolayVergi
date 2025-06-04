package com.kolayvergi.strategy.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.*;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.strategy.VergiHesaplamaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MtvVergisiHesaplamaStrategy implements VergiHesaplamaStrategy {
    private final MessageSource messageSource;

    @Override
    public Vergi hesapla(Alisveris alisveris, Kullanici kullanici, Vergi... oncekiVergiler) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalArgumentException(
                    messageSource.getMessage("vergi.mtv_arac_bilgisi_gerekli", null, LocaleContextHolder.getLocale()));
        }

        BigDecimal temelMtvTutari = switch (aracBilgisi.getAracTipi()) {
            case OTOMOBIL_KAPTIKACTI_ARAZI -> hesaplaOtomobil(aracBilgisi.getAracYasi(), aracBilgisi.getMotorSilindirHacmi());
            case MINIBUS -> hesaplaMinibus(aracBilgisi.getAracYasi());
            case OTOBUS -> hesaplaOtobus(aracBilgisi.getAracYasi(), aracBilgisi.getAracKapasitesi());
            case KAMYON_KAMYONET_CEKICI -> hesaplaKamyon(aracBilgisi.getAracYasi(), aracBilgisi.getAracAgirligi());
        };

        BigDecimal finalMtvTutari = uygulaKullaniciIndirimleri(temelMtvTutari, kullanici);

        MtvVergisi mtvVergisi = new MtvVergisi();
        mtvVergisi.setTutar(finalMtvTutari);
        mtvVergisi.setAlisveris(alisveris);
        mtvVergisi.setAracBilgisi(aracBilgisi);
        mtvVergisi.setMatrah(alisveris.getTutar());
        mtvVergisi.setOran(BigDecimal.ONE); 

        return mtvVergisi;
    }

    private BigDecimal hesaplaOtomobil(AracYasi yas, MotorSilindirHacmi motorSilindirHacmi) {
        if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_0_1300)) {
            return getDeger(yas, 3359, 2343, 1308, 987, 347);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_1301_1600)) {
            return getDeger(yas, 5851, 4387, 2544, 1798, 690);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_1601_1800)) {
            return getDeger(yas, 11374, 8894, 5227, 3189, 1235);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_1801_2000)) {
            return getDeger(yas, 17920, 13800, 8111, 4828, 1898);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_2001_2500)) {
            return getDeger(yas, 26885, 19517, 12193, 7282, 2880);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_2501_3000)) {
            return getDeger(yas, 37485, 32615, 20373, 10957, 4016);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_3001_3500)) {
            return getDeger(yas, 57093, 51374, 30944, 15446, 5657);
        } else if (motorSilindirHacmi.equals(MotorSilindirHacmi.CM3_3501_4000)) {
            return getDeger(yas, 89767, 77517, 45649, 20373, 8111);
        } else {
            return getDeger(yas, 146932, 110177, 65252, 29326, 11374);
        }
    }

    private BigDecimal hesaplaMinibus(AracYasi yas) {
        return switch (yas) {
            case AracYasi.BIR_UC_YAS, AracYasi.DORT_ALTI_YAS -> BigDecimal.valueOf(4016);
            case AracYasi.YEDI_ONBIR_YAS, AracYasi.ONIKI_ONBES_YAS -> BigDecimal.valueOf(2652);
            case AracYasi.ONALTI_USTU_YAS -> BigDecimal.valueOf(1294);
        };
    }

    private BigDecimal hesaplaOtobus(AracYasi yas, AracKapasitesi aracKapasitesi) {
        if (aracKapasitesi.equals(AracKapasitesi.KISI_1_25)) {
            return getDeger(yas, 10146, 6059, 2652);
        } else if (aracKapasitesi.equals(AracKapasitesi.KISI_26_35)) {
            return getDeger(yas, 12168, 10146, 4016);
        } else if (aracKapasitesi.equals(AracKapasitesi.KISI_36_45)) {
            return getDeger(yas, 13541, 11485, 5355);
        } else {
            return getDeger(yas, 16245, 13541, 8106);
        }
    }

    private BigDecimal hesaplaKamyon(AracYasi yas, AracAgirligi aracAgirligi) {
        if (aracAgirligi.equals(AracAgirligi.KG_1_1500)) {
            return getDeger(yas, 3601, 2392, 1171);
        } else if (aracAgirligi.equals(AracAgirligi.KG_1501_3500)) {
            return getDeger(yas, 7295, 4226, 2392);
        } else if (aracAgirligi.equals(AracAgirligi.KG_3501_5000)) {
            return getDeger(yas, 10960, 9122, 3601);
        } else if (aracAgirligi.equals(AracAgirligi.KG_5001_10000)) {
            return getDeger(yas, 12168, 10333, 4844);
        } else {
            return getDeger(yas, 14624, 0, 0); 
        }
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutMtvTutari, Kullanici kullanici) {
        BigDecimal yeniMtvTutari = mevcutMtvTutari;

        if (kullanici.getYas() != null && kullanici.getYas() < 25) {
            yeniMtvTutari = yeniMtvTutari.subtract(BigDecimal.valueOf(1));
        }

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN && kullanici.getYas() != null && kullanici.getYas() > 40) {
            yeniMtvTutari = yeniMtvTutari.subtract(BigDecimal.valueOf(0.5));
        }

        if (kullanici.getMeslek() == Meslek.OGRETMEN || kullanici.getMeslek() == Meslek.MEMUR) {
            yeniMtvTutari = yeniMtvTutari.subtract(BigDecimal.valueOf(1));
        }

        if (yeniMtvTutari.compareTo(BigDecimal.ZERO) < 0) {
            yeniMtvTutari = BigDecimal.ZERO;
        }

        return yeniMtvTutari;
    }

    private BigDecimal getDeger(AracYasi yas, int bir_uc, int dort_alti, int yedi_onbir, int oniki_onbes, int onalti_ustu) {
        return switch (yas) {
            case AracYasi.BIR_UC_YAS -> BigDecimal.valueOf(bir_uc);
            case AracYasi.DORT_ALTI_YAS -> BigDecimal.valueOf(dort_alti);
            case AracYasi.YEDI_ONBIR_YAS -> BigDecimal.valueOf(yedi_onbir);
            case AracYasi.ONIKI_ONBES_YAS -> BigDecimal.valueOf(oniki_onbes);
            case AracYasi.ONALTI_USTU_YAS -> BigDecimal.valueOf(onalti_ustu);
        };
    }

    private BigDecimal getDeger(AracYasi yas, int bir_alti, int yedi_onbes, int onalti_ustu) {
        return switch (yas) {
            case AracYasi.BIR_UC_YAS, AracYasi.DORT_ALTI_YAS -> BigDecimal.valueOf(bir_alti);
            case AracYasi.YEDI_ONBIR_YAS, AracYasi.ONIKI_ONBES_YAS -> BigDecimal.valueOf(yedi_onbes);
            case AracYasi.ONALTI_USTU_YAS -> BigDecimal.valueOf(onalti_ustu);
        };
    }
} 