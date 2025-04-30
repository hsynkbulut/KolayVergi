package com.kolayvergi.odemeYontemi;

import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class KrediKartiOdeme extends FaizliOdeme {
    public KrediKartiOdeme(TaksitService taksitService, OdemePlaniService odemePlaniService, BorcService borcService) {
        super(BigDecimal.valueOf(4), taksitService, odemePlaniService, borcService);
    }
}
