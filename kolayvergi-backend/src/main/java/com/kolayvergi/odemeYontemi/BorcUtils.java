package com.kolayvergi.odemeYontemi;

import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BorcUtils {
    private final BorcService borcService;
    private final TaksitService taksitService;

    public void kalanBorcuGuncelle(Long kullaniciId, BigDecimal odemeTutari) {
        BorcResponse dbBorc = borcService.getBorcByKullaniciId(kullaniciId);
        if (dbBorc != null) {
            BorcUpdateRequest update = new BorcUpdateRequest();
            update.setKullaniciId(kullaniciId);
            update.setKalanBorc(dbBorc.getKalanBorc().subtract(odemeTutari));
            borcService.updateBorc(dbBorc.getId(), update);
        }
    }
}
