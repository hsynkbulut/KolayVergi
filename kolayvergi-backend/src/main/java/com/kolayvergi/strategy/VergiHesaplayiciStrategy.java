package com.kolayvergi.strategy;

import com.kolayvergi.entity.Kullanici;

import java.math.BigDecimal;

public interface VergiHesaplayiciStrategy {

    BigDecimal hesapla(BigDecimal alisverisTutari, Kullanici kullanici);
}
