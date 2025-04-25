package com.kolayvergi.service;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.util.List;

public interface TaksitService {

    List<Taksit> createInitialTaksitler(OdemePlani odemePlani, List<OdemeTuru> odemeTurleri);
    Taksit updateTaksitOdeme(Long taksitId);


}
