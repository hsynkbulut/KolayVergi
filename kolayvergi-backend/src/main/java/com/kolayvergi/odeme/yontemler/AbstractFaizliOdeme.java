package com.kolayvergi.odeme.yontemler;

import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odeme.utils.BorcUtils;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractFaizliOdeme implements OdemeYontemi {

    protected final BigDecimal faizOrani;
    protected final TaksitService taksitService;
    protected final OdemePlaniService odemePlaniService;
    protected final BorcService borcService;
    protected final BorcUtils borcUtils;
    protected final KullaniciService kullaniciService;
    protected final MessageSource messageSource;


    @Override
    public OdemeSonucu hesaplaVeOde(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi, BigDecimal kullaniciOdemeTutari) {
        OdemeSonucu sonuc = hesapla(taksit, odemeTarihi);

        if (sonuc.getGuncellenmisTutar().compareTo(kullaniciOdemeTutari) != 0) {
            throw new IllegalArgumentException(
                messageSource.getMessage("odeme.tutar_farkli", null, LocaleContextHolder.getLocale())
            );
        }

        BigDecimal guncellenmisTutar = sonuc.getGuncellenmisTutar();

        taksitService.updateTaksitForPayment(taksit, odemeTuru, guncellenmisTutar);
        odemePlaniService.updateOdemePlaniAfterPayment(taksit, guncellenmisTutar);
        Kullanici kullanici = kullaniciService.getCurrentUser();
        UUID kullaniciId = kullanici.getId();

        borcUtils.kalanBorcuGuncelle(kullaniciId, sonuc.getMevcutTaksitTutari());
        return sonuc;
    }

    @Override
    public OdemeSonucu sadeceHesapla(Taksit taksit, LocalDate odemeTarihi) {
        return hesapla(taksit, odemeTarihi);
    }

    protected OdemeSonucu hesapla(Taksit taksit, LocalDate odemeTarihi) {
        BigDecimal mevcut = taksit.getTaksitTutari();
        BigDecimal faiz = mevcut.multiply(faizOrani).divide(BigDecimal.valueOf(100));
        BigDecimal indirim = BigDecimal.ZERO;
        BigDecimal ceza = BigDecimal.ZERO;
        boolean indirimVar = false;
        boolean cezaVar = false;

        if (odemeTarihi.isBefore(taksit.getSonOdemeTarihi())) {
            indirim = mevcut.multiply(BigDecimal.valueOf(0.05));
            indirimVar = true;
        } else if (odemeTarihi.isAfter(taksit.getSonOdemeTarihi())) {
            ceza = mevcut.multiply(BigDecimal.valueOf(0.10));
            cezaVar = true;
        }

        BigDecimal toplam = mevcut.add(faiz).add(ceza).subtract(indirim);

        return new OdemeSonucu(mevcut, toplam, faiz, indirim, ceza, true, indirimVar, cezaVar);
    }
}