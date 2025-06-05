package com.kolayvergi.service;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.Kullanici;

import java.math.BigDecimal;

public interface OdemePlaniService {
    OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, BigDecimal odenecekTutar);
    OdemePlani updateOdemePlaniAfterPayment(Taksit taksit, BigDecimal guncellenmisTutar);
    OdemePlani updateOdemePlani(Alisveris alisveris, Kullanici kullanici, BigDecimal guncellenmisTutar);
}
