package com.kolayvergi.factory.impl;

import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.factory.VergiTuruBelirleyici;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class VergiTuruBelirleyiciImpl implements VergiTuruBelirleyici {

    @Override
    public List<VergiTuru> getVergiTurleri(UrunTuru urunTuru) {
        List<VergiTuru> vergiTurleri = new ArrayList<>();

        switch (urunTuru) {
            case OTOMOBIL -> {
                vergiTurleri.add(VergiTuru.OTV);
                vergiTurleri.add(VergiTuru.KDV);
                vergiTurleri.add(VergiTuru.MTV);
            }
            case GIDA, KITAP -> {
                vergiTurleri.add(VergiTuru.KDV);
            }
            case ELEKTRONIK, GIYIM, BEYAZ_ESYA, KOZMETIK, MOBILYA -> {
                vergiTurleri.add(VergiTuru.KDV);
                if (urunTuru.getLuksKatSayisi().compareTo(BigDecimal.ONE) > 0) {
                    vergiTurleri.add(VergiTuru.OTV);
                }
            }
        }

        return vergiTurleri;
    }
}
