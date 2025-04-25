package com.kolayvergi.service.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;

import java.math.BigDecimal;

public interface VergiHesaplayici {
    BigDecimal hesapla(Alisveris alisveris, Kullanici kullanici);
}

