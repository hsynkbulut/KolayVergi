package com.kolayvergi.service;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TaksitService {

    List<Taksit> createInitialTaksitler(UUID kullaniciId, OdemePlani odemePlani);
    
    Taksit getTaksitByTaksitNo(String taksitNo);

    Taksit updateTaksitForPayment(Taksit taksit, OdemeTuru odemeTuru, BigDecimal guncellenmisTutar);
}
