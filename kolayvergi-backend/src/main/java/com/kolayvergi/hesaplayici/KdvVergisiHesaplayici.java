package com.kolayvergi.hesaplayici;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class KdvVergisiHesaplayici {

    public KdvVergisi hesapla(Alisveris alisveris, Kullanici kullanici) {

        BigDecimal temelOran = getUrunTuruKdvOrani(alisveris.getUrunTuru());

        BigDecimal finalOran = uygulaKullaniciIndirimleri(temelOran, kullanici);

        BigDecimal kdvTutari = alisveris.getTutar()
                .multiply(finalOran)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        KdvVergisi kdvVergisi = new KdvVergisi();
        kdvVergisi.setTutar(kdvTutari);
        kdvVergisi.setUrunTuru(alisveris.getUrunTuru());
        kdvVergisi.setAlisveris(alisveris);

        return kdvVergisi;
    }

    private BigDecimal getUrunTuruKdvOrani(UrunTuru urunTuru) {
        return switch (urunTuru) {
            case GIDA -> BigDecimal.valueOf(8);
            case ELEKTRONIK, GIYIM, BEYAZ_ESYA -> BigDecimal.valueOf(18);
            case KITAP -> BigDecimal.valueOf(5);
            case KOZMETIK -> BigDecimal.valueOf(20);
            case MOBILYA -> BigDecimal.valueOf(12);
            case OTOMOBIL -> BigDecimal.valueOf(18);
            default -> throw new IllegalArgumentException("Ürün türü için KDV oranı tanımlı değil: " + urunTuru);
        };
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutOran, Kullanici kullanici) {
        BigDecimal yeniOran = mevcutOran;

        if (kullanici.getYas() < 25) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(2));
        }

        if (kullanici.getMeslek().name().equals("OGRETMEN")) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(3));
        }

        if (kullanici.getCinsiyet().name().equals("KADIN") && kullanici.getYas() > 40) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(1));
        }

        if (yeniOran.compareTo(BigDecimal.ZERO) < 0) {
            yeniOran = BigDecimal.ZERO;
        }

        return yeniOran;
    }
}
