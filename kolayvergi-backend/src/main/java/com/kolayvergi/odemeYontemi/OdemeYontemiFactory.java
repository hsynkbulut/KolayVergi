package com.kolayvergi.odemeYontemi.factory;

import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odemeYontemi.KrediKartiOdeme;
import com.kolayvergi.odemeYontemi.KrediOdeme;
import com.kolayvergi.odemeYontemi.NakitOdeme;
import com.kolayvergi.odemeYontemi.OdemeYontemi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OdemeYontemiFactory {

    private final Map<OdemeTuru, OdemeYontemi> odemeYontemiMap = new HashMap<>();

    public OdemeYontemiFactory(List<OdemeYontemi> odemeYontemleri) {
        for (OdemeYontemi yontem : odemeYontemleri) {
            if (yontem instanceof NakitOdeme) {
                odemeYontemiMap.put(OdemeTuru.NAKIT, yontem);
            } else if (yontem instanceof KrediOdeme) {
                odemeYontemiMap.put(OdemeTuru.KREDI, yontem);
            } else if (yontem instanceof KrediKartiOdeme) {
                odemeYontemiMap.put(OdemeTuru.KREDI_KARTI, yontem);
            }
        }
    }

    public OdemeYontemi getYontem(OdemeTuru odemeTuru) {
        OdemeYontemi yontem = odemeYontemiMap.get(odemeTuru);
        if (yontem == null) {
            throw new IllegalArgumentException("Geçersiz ödeme türü: " + odemeTuru);
        }
        return yontem;
    }
}
