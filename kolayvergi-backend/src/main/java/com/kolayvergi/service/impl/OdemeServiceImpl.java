package com.kolayvergi.service.impl;

import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odemeYontemi.NakitOdeme;
import com.kolayvergi.odemeYontemi.OdemeYontemi;
import com.kolayvergi.service.OdemeService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OdemeServiceImpl implements OdemeService {

    private final TaksitService taksitService;
    private final NakitOdeme nakitOdeme;

    @Override
    public boolean taksitOde(TaksitOdemeRequest request) {
//        TODO: OdenmisTaksit tekrar odenmememli-kontrol eklenek

        Taksit taksit = taksitService.getTaksitByTaksitNo(request.getTaksitNo());

        // Ödeme türüne göre uygun yöntemi seç
        OdemeYontemi odemeYontemi = getOdemeYontemi(request.getOdemeTuru());

        // Ödeme işlemini gerçekleştir
        return odemeYontemi.odemeYap(taksit, request.getOdemeTutari(), request.getOdemeTuru());
    }

    private OdemeYontemi getOdemeYontemi(OdemeTuru odemeTuru) {
        return switch (odemeTuru) {
            case NAKIT -> nakitOdeme;
            case KREDI -> throw new UnsupportedOperationException("Kredi ödemesi henüz desteklenmiyor");
            case KREDI_KARTI -> throw new UnsupportedOperationException("Kredi kartı ödemesi henüz desteklenmiyor");
        };
    }

}