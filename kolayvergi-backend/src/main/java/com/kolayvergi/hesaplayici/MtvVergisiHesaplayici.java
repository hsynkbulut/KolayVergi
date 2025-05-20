package com.kolayvergi.hesaplayici;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import com.kolayvergi.entity.vergi.MtvVergisi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class MtvVergisiHesaplayici {

    public MtvVergisi hesapla(Alisveris alisveris, Kullanici kullanici) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalArgumentException("MTV Vergisi hesaplanabilmesi için araç bilgisi bulunmalıdır.");
        }

        // Temel oranı araç bilgilerine göre hesaplama
        BigDecimal temelOran = getTemelMtvOrani(aracBilgisi);

        BigDecimal finalOran = uygulaKullaniciIndirimleri(temelOran, kullanici);

        BigDecimal mtvTutari = alisveris.getTutar()
                .multiply(finalOran)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        MtvVergisi mtvVergisi = new MtvVergisi();
        mtvVergisi.setTutar(mtvTutari);
        mtvVergisi.setAlisveris(alisveris);
        mtvVergisi.setAracTipi(aracBilgisi.getAracTipi());
        mtvVergisi.setAracYasi(aracBilgisi.getAracYasi());
        mtvVergisi.setMotorSilindirHacmi(aracBilgisi.getMotorSilindirHacmi());
        mtvVergisi.setIlkTescilYili(aracBilgisi.getIlkTescilYili());

        return mtvVergisi;
    }

    private BigDecimal getTemelMtvOrani(AracBilgisi aracBilgisi) {
        BigDecimal oran = BigDecimal.valueOf(5);

        // Motor hacmine göre artırım
        if (aracBilgisi.getMotorSilindirHacmi() != null) {
            if (aracBilgisi.getMotorSilindirHacmi() > 2000) {
                oran = oran.add(BigDecimal.valueOf(5));
            } else if (aracBilgisi.getMotorSilindirHacmi() > 1600) {
                oran = oran.add(BigDecimal.valueOf(3));
            }
        }

        // Araç yaşına göre indirim
        if (aracBilgisi.getAracYasi() != null) {
            if (aracBilgisi.getAracYasi() > 10) {
                oran = oran.subtract(BigDecimal.valueOf(1));
            }
        }

        // Araç tipi binek değilse farklı oran
        if (aracBilgisi.getAracTipi() != null && !aracBilgisi.getAracTipi().equalsIgnoreCase("Binek")) {
            oran = oran.add(BigDecimal.valueOf(2)); // +2% ek
        }

        if (oran.compareTo(BigDecimal.ZERO) < 0) {
            oran = BigDecimal.ZERO;
        }

        return oran;
    }

    private BigDecimal uygulaKullaniciIndirimleri(BigDecimal mevcutOran, Kullanici kullanici) {
        BigDecimal yeniOran = mevcutOran;

        if (kullanici.getYas() != null && kullanici.getYas() < 25) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(1));
        }

        if (kullanici.getCinsiyet() == Cinsiyet.KADIN && kullanici.getYas() != null && kullanici.getYas() > 40) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(0.5));
        }

        if (kullanici.getMeslek() == Meslek.OGRETMEN || kullanici.getMeslek() == Meslek.MEMUR) {
            yeniOran = yeniOran.subtract(BigDecimal.valueOf(1));
        }

        if (yeniOran.compareTo(BigDecimal.ZERO) < 0) {
            yeniOran = BigDecimal.ZERO;
        }

        return yeniOran;
    }
}
