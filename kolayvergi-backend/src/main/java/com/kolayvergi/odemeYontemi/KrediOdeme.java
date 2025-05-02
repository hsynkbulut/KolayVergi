package com.kolayvergi.odemeYontemi;

import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// Faizli ödeme türleri implementasyonu
@Component
public class KrediOdeme extends AbstractFaizliOdeme {
    public KrediOdeme(TaksitService taksitService, OdemePlaniService odemePlaniService, BorcService borcService) {
        super(BigDecimal.valueOf(2), taksitService, odemePlaniService, borcService);
    }
}
