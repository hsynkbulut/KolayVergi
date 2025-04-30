package com.kolayvergi.service;

import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;

public interface OdemeService {

    //boolean taksitOde(String taksitNo, BigDecimal odemeTutari, OdemeTuru odemeTuru);

//    boolean taksitOde(TaksitOdemeRequest request);
    OdemeSonucu taksitOde(TaksitOdemeRequest request);
    OdemeSonucu taksitOdemeBilgisi(String taksitNo, OdemeTuru odemeTuru);


}