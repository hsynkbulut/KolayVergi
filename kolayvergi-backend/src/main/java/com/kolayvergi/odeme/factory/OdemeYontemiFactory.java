package com.kolayvergi.odeme.factory;

import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.odeme.yontemler.KrediKartiOdeme;
import com.kolayvergi.odeme.yontemler.KrediOdeme;
import com.kolayvergi.odeme.yontemler.NakitOdeme;
import com.kolayvergi.odeme.yontemler.OdemeYontemi;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class OdemeYontemiFactory {

    private final Map<OdemeTuru, OdemeYontemi> odemeYontemiMap = new EnumMap<>(OdemeTuru.class);
    private final MessageSource messageSource;

    public OdemeYontemiFactory(List<OdemeYontemi> odemeYontemleri, MessageSource messageSource) {
        this.messageSource = messageSource;
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
        if (ObjectUtils.isEmpty(yontem)) {
            throw new IllegalArgumentException(
                messageSource.getMessage("odeme.odeme_turu_gecersiz", new Object[]{odemeTuru},
                        LocaleContextHolder.getLocale())
            );
        }
        return yontem;
    }
}
