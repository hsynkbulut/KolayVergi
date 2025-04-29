package com.kolayvergi.odemeYontemi;

import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class KrediOdeme implements OdemeYontemi {

    private final TaksitService taksitService;
    private final OdemePlaniService odemePlaniService;

    @Override
    public boolean odemeYap(Taksit taksit, BigDecimal odemeTutari, OdemeTuru odemeTuru) {
        if (odemeTutari.compareTo(taksit.getTaksitTutari()) != 0) {
            return false;
        }

        taksitService.updateTaksitForPayment(taksit, odemeTuru);
        odemePlaniService.updateOdemePlaniAfterPayment(taksit);

        // TODO: Borç ve vergi kasası güncellemeleri

        return true;
    }
}
