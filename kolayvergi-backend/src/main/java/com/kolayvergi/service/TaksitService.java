package com.kolayvergi.service;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;
import java.util.List;

public interface TaksitService {

    List<Taksit> createInitialTaksitler(Long kullaniciId, OdemePlani odemePlani);
    
    Taksit getTaksitByTaksitNo(String taksitNo);

    Taksit updateTaksitForPayment(Taksit taksit, OdemeTuru odemeTuru, BigDecimal guncellenmisTutar);
}
