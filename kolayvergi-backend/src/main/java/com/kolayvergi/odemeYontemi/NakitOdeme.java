package com.kolayvergi.odemeYontemi;

import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class NakitOdeme implements OdemeYontemi {

    private final TaksitService taksitService;
    private final OdemePlaniService odemePlaniService;
    private final BorcService borcService;

    @Override
    public boolean odemeYap(Taksit taksit, BigDecimal odemeTutari, OdemeTuru odemeTuru) {

        // Taksit tutarını kontrol et
        if (odemeTutari.compareTo(taksit.getTaksitTutari()) != 0) {
            return false;
        }

        // Taksiti güncelle
        taksitService.updateTaksitForPayment(taksit, odemeTuru);

        // Ödeme planını güncelle
        odemePlaniService.updateOdemePlaniAfterPayment(taksit);


        //TODO: Borç bilgisini + Vergi Kasasi güncelle
//        borcService.updateBorc(taksit.getOdemePlani().getAlisveris().getKullanici().getId(),
//            new com.kolayvergi.dto.request.BorcUpdateRequest(odemeTutari));

        return true;
    }
}
