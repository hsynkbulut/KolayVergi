package com.kolayvergi.odeme.yontemler;

import com.kolayvergi.odeme.utils.BorcUtils;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class KrediKartiOdeme extends AbstractFaizliOdeme {
    public KrediKartiOdeme(TaksitService taksitService, OdemePlaniService odemePlaniService, BorcService borcService, BorcUtils borcUtils, KullaniciService kullaniciService) {
        super(BigDecimal.valueOf(4), taksitService, odemePlaniService, borcService, borcUtils, kullaniciService);
    }
}