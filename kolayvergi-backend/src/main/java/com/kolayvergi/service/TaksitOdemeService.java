package com.kolayvergi.service;

import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;

public interface TaksitOdemeService {
//    boolean taksitOdemeYap(String taksitNo, BigDecimal odemeTutari, OdemeTuru odemeTuru);
//    boolean taksitOdemeYap(TaksitOdemeRequest request);

    OdemeSonucu taksitOdemeYap(TaksitOdemeRequest request);
    OdemeSonucu taksitOdemeDetaylariniGetir(String taksitNo, OdemeTuru odemeTuru);
}