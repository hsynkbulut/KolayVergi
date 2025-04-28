package com.kolayvergi.hesaplayici;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.AracOtvVergisi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class AracOtvVergisiHesaplayici {

    public AracOtvVergisi hesapla(Alisveris alisveris, Kullanici kullanici) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();

        if (aracBilgisi == null) {
            throw new IllegalArgumentException("Araç bilgisi bulunamadı. Araç ÖTV hesaplanamaz.");
        }

        BigDecimal temelOran = BigDecimal.valueOf(45); // %45 baz oran

        // Motor Hacmine göre ayarlamalar
        if (aracBilgisi.getMotorSilindirHacmi() != null) {
            if (aracBilgisi.getMotorSilindirHacmi() > 2000) {
                temelOran = temelOran.multiply(BigDecimal.valueOf(1.5)); // +%50
            } else if (aracBilgisi.getMotorSilindirHacmi() >= 1000) {
                temelOran = temelOran.multiply(BigDecimal.valueOf(1.2)); // +%20
            }
        }

        // Araç Tipine göre
        if ("SPOR".equalsIgnoreCase(aracBilgisi.getAracTipi())) {
            temelOran = temelOran.multiply(BigDecimal.valueOf(1.3)); // +%30
        }

        // Kullanıcı özelliklerine göre indirimler
        temelOran = uygulaKullaniciIndirimleri(temelOran, kullanici);

        // ÖTV tutarını hesapla
        BigDecimal otvTutari = alisveris.getTutar()
                .multiply(temelOran)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // AracOtvVergisi nesnesi oluştur
        AracOtvVergisi aracOtvVergisi = new AracOtvVergisi();
        aracOtvVergisi.setFiyat(otvTutari);
        aracOtvVergisi.setAlisveris(alisveris);
        aracOtvVergisi.setAracTipi(aracBilgisi.getAracTipi());
        aracOtvVergisi.setMotorSilindirHacmi(aracBilgisi.getMotorSilindirHacmi());

        return aracOtvVergisi;
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutOran, Kullanici kullanici) {
        BigDecimal yeniOran = mevcutOran;

        if (kullanici.getYas() < 25) {
            yeniOran = yeniOran.subtract(mevcutOran.multiply(BigDecimal.valueOf(0.05))); // %5 indirim
        }

        if ("OGRETMEN".equalsIgnoreCase(kullanici.getMeslek().name())) {
            yeniOran = yeniOran.subtract(mevcutOran.multiply(BigDecimal.valueOf(0.03))); // %3 indirim
        }

        if ("KADIN".equalsIgnoreCase(kullanici.getCinsiyet().name()) && kullanici.getYas() > 40) {
            yeniOran = yeniOran.subtract(mevcutOran.multiply(BigDecimal.valueOf(0.02))); // %2 indirim
        }

        if (yeniOran.compareTo(BigDecimal.ZERO) < 0) {
            yeniOran = BigDecimal.ZERO;
        }

        return yeniOran;
    }
}
