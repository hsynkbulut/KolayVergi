package com.kolayvergi.service;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;

import java.util.List;

public interface TaksitService {

    List<Taksit> createInitialTaksitler(Long kullaniciId,OdemePlani odemePlani);
}
