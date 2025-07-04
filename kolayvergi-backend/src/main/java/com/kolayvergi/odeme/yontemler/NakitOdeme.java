package com.kolayvergi.odeme.yontemler;

import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class NakitOdeme implements OdemeYontemi {
    private final TaksitService taksitService;
    private final OdemePlaniService odemePlaniService;
    private final BorcService borcService;
    protected final KullaniciService kullaniciService;
    protected final MessageSource messageSource;

    @Override
    public OdemeSonucu hesaplaVeOde(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi, BigDecimal kullaniciOdemeTutari) {
        OdemeSonucu sonuc = sadeceHesapla(taksit, odemeTarihi);

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

        borcService.kalanBorcuGuncelle(kullaniciId, sonuc.getMevcutTaksitTutari());

        return sonuc;
    }

    @Override
    public OdemeSonucu sadeceHesapla(Taksit taksit, LocalDate odemeTarihi) {
        BigDecimal mevcut = taksit.getTaksitTutari();
        BigDecimal indirim = BigDecimal.ZERO;
        BigDecimal ceza = BigDecimal.ZERO;
        boolean indirimVar = false;
        boolean cezaVar = false;

        LocalDate sonOdemeTarihi = taksit.getSonOdemeTarihi();

        if (odemeTarihi.isBefore(sonOdemeTarihi)) {
            indirim = mevcut.multiply(BigDecimal.valueOf(0.03));
            indirimVar = true;
        } else if (odemeTarihi.isAfter(sonOdemeTarihi)) {
            ceza = mevcut.multiply(BigDecimal.valueOf(0.05));
            cezaVar = true;
        }

        BigDecimal toplam = mevcut.add(ceza).subtract(indirim);

        return new OdemeSonucu(mevcut, toplam, BigDecimal.ZERO, indirim, ceza, false, indirimVar, cezaVar);
    }
}