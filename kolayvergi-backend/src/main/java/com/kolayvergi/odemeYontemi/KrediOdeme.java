package com.kolayvergi.odemeYontemi;

import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class KrediOdeme extends AbstractFaizliOdeme {
    public KrediOdeme( TaksitService taksitService, OdemePlaniService odemePlaniService, BorcService borcService, BorcUtils borcUtils, KullaniciService kullaniciService) {
        super(BigDecimal.valueOf(2), taksitService, odemePlaniService, borcService, borcUtils, kullaniciService);
    }
}
