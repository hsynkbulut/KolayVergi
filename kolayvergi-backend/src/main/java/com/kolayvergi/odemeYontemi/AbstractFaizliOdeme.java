package com.kolayvergi.odemeYontemi;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public abstract class AbstractFaizliOdeme implements OdemeYontemi {

    protected final BigDecimal faizOrani;
    protected final TaksitService taksitService;
    protected final OdemePlaniService odemePlaniService;
    protected final BorcService borcService;


    @Override
    public OdemeSonucu hesaplaVeOde(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi, BigDecimal kullaniciOdemeTutari) {
        OdemeSonucu sonuc = hesapla(taksit, odemeTuru, odemeTarihi);

        if (sonuc.getGuncellenmisTutar().compareTo(kullaniciOdemeTutari) != 0) {
            throw new IllegalArgumentException("Girilen tutar beklenen tutardan farkli!");
        }

        BigDecimal guncellenmisTutar = sonuc.getGuncellenmisTutar();

        taksitService.updateTaksitForPayment(taksit, odemeTuru, guncellenmisTutar);
        odemePlaniService.updateOdemePlaniAfterPayment(taksit, guncellenmisTutar);

        //Borc
//TODO: Bu borc kismi refactor edilebilir aynisi taksitServiceImpl dede var.
        Long kullaniciId = taksit.getOdemePlani().getAlisveris().getKullanici().getId();

        BorcUpdateRequest borcUpdateRequest = new BorcUpdateRequest();
        BorcResponse dbBorc = borcService.getBorcByKullaniciId(kullaniciId);
        if(dbBorc != null) {
            borcUpdateRequest.setKullaniciId(kullaniciId);
            borcUpdateRequest.setKalanBorc(dbBorc.getKalanBorc().subtract(guncellenmisTutar));
            borcService.updateBorc(dbBorc.getId(), borcUpdateRequest);
        }

        return sonuc;
    }

    @Override
    public OdemeSonucu sadeceHesapla(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi) {
        return hesapla(taksit, odemeTuru, odemeTarihi);
    }

    protected OdemeSonucu hesapla(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi) {
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
