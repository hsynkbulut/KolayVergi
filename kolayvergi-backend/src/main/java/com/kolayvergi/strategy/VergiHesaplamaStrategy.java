package com.kolayvergi.strategy;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.Vergi;

public interface VergiHesaplamaStrategy {
    Vergi hesapla(Alisveris alisveris, Kullanici kullanici, Vergi... oncekiVergiler);
} 