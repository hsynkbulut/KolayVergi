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
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class NakitOdeme implements OdemeYontemi {
    private final TaksitService taksitService;
    private final OdemePlaniService odemePlaniService;
    private final BorcService borcService;

    //TODO: NakitOdeme ve abstract faizli odemede cok benzer 2 method var. Refactor edilecek.
    @Override
    public OdemeSonucu hesaplaVeOde(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi, BigDecimal kullaniciOdemeTutari) {
        OdemeSonucu sonuc = sadeceHesapla(taksit, odemeTuru, odemeTarihi);

        if (sonuc.getGuncellenmisTutar().compareTo(kullaniciOdemeTutari) != 0) {
            throw new IllegalArgumentException("Girilen tutar beklenen tutardan farkli!");
        }

        BigDecimal guncellenmisTutar = sonuc.getGuncellenmisTutar();

        taksitService.updateTaksitForPayment(taksit, odemeTuru, guncellenmisTutar);
        odemePlaniService.updateOdemePlaniAfterPayment(taksit, guncellenmisTutar);

        //TODO: Bu borc kismi refactor edilebilir aynisi taksitServiceImpl ve AbstractFaizli odeme dede var.
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
