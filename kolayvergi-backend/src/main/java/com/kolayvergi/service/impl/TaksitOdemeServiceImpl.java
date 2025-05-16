package com.kolayvergi.service.impl;

import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odemeYontemi.KrediKartiOdeme;
import com.kolayvergi.odemeYontemi.KrediOdeme;
import com.kolayvergi.odemeYontemi.NakitOdeme;
import com.kolayvergi.odemeYontemi.OdemeYontemi;
import com.kolayvergi.service.TaksitOdemeService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TaksitOdemeServiceImpl implements TaksitOdemeService {

    private final TaksitService taksitService;
    private final NakitOdeme nakitOdeme;
    private final KrediOdeme krediOdeme;
    private final KrediKartiOdeme krediKartiOdeme;


    private OdemeYontemi getOdemeYontemi(OdemeTuru odemeTuru) {
        return switch (odemeTuru) {
            case NAKIT -> nakitOdeme;
            case KREDI -> krediOdeme;
            case KREDI_KARTI -> krediKartiOdeme;
        };
    }

    @Transactional
    @Override
    public OdemeSonucu taksitOdemeYap(TaksitOdemeRequest request) {
        Taksit taksit = taksitService.getTaksitByTaksitNo(request.getTaksitNo());
        OdemeYontemi odemeYontemi = getOdemeYontemi(request.getOdemeTuru());
        return odemeYontemi.hesaplaVeOde(taksit, request.getOdemeTuru(), LocalDate.now(), request.getOdemeTutari());
    }

    @Override
    public OdemeSonucu taksitOdemeDetaylariniGetir(String taksitNo, OdemeTuru odemeTuru) {
        Taksit taksit = taksitService.getTaksitByTaksitNo(taksitNo);
        OdemeYontemi odemeYontemi = getOdemeYontemi(odemeTuru);
        return odemeYontemi.sadeceHesapla(taksit, LocalDate.now());
    }
}