package com.kolayvergi.odeme.factory;

import com.kolayvergi.constant.OdemeConstants;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odeme.yontemler.KrediKartiOdeme;
import com.kolayvergi.odeme.yontemler.KrediOdeme;
import com.kolayvergi.odeme.yontemler.NakitOdeme;
import com.kolayvergi.odeme.yontemler.OdemeYontemi;
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
            throw new IllegalArgumentException(String.format(OdemeConstants.ODEME_TURU_GECERSIZ, odemeTuru));
        }
        return yontem;
    }
}
