package com.kolayvergi.service.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;

import java.math.BigDecimal;

public interface VergiHesaplamaService {
    VergiHesaplamaSonuc hesaplaVergiler(Alisveris alisveris, Kullanici kullanici);
} 