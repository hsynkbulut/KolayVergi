package com.kolayvergi.factory.impl;

import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.factory.VergiTuruBelirleyici;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultVergiTuruBelirleyici implements VergiTuruBelirleyici {

    @Override
    public List<VergiTuru> getVergiTurleri(UrunTuru urunTuru) {
        return switch (urunTuru) {
            case GIDA -> List.of(VergiTuru.KDV);
            case OTOMOBIL -> List.of(VergiTuru.KDV, VergiTuru.OTV, VergiTuru.MTV);
            case ELEKTRONIK, GIYIM, KOZMETIK -> List.of(VergiTuru.KDV);
            case MOBILYA, BEYAZ_ESYA -> List.of(VergiTuru.KDV);
            case KITAP -> List.of(); // Kitap vergiden muaf gibi kabul edebiliriz
        };
    }
}
