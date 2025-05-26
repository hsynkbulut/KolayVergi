package com.kolayvergi.service.impl;

import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odeme.factory.OdemeYontemiFactory;
import com.kolayvergi.odeme.yontemler.OdemeYontemi;
import com.kolayvergi.service.TaksitOdemeService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaksitOdemeServiceImpl implements TaksitOdemeService {

    private final TaksitService taksitService;
    private final OdemeYontemiFactory odemeYontemiFactory;

    @Transactional
    @Override
    public OdemeSonucu taksitOdemeYap(TaksitOdemeRequest request) {
        Taksit taksit = taksitService.getTaksitByTaksitNo(request.getTaksitNo());
        // Strateji (ödeme yöntemi) seç
        OdemeYontemi odemeYontemi = odemeYontemiFactory.getYontem(request.getOdemeTuru());
        return odemeYontemi.hesaplaVeOde(taksit, request.getOdemeTuru(), LocalDate.now(), request.getOdemeTutari());
    }

    @Override
    public OdemeSonucu taksitOdemeDetaylariniGetir(String taksitNo, OdemeTuru odemeTuru) {
        Taksit taksit = taksitService.getTaksitByTaksitNo(taksitNo);
        // Strateji (ödeme yöntemi) seç
        OdemeYontemi odemeYontemi = odemeYontemiFactory.getYontem(odemeTuru);
        return odemeYontemi.sadeceHesapla(taksit, LocalDate.now());
    }
}
