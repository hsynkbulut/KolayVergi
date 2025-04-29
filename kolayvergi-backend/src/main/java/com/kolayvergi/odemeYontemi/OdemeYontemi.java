package com.kolayvergi.odemeYontemi;

import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;

public interface OdemeYontemi {
    boolean odemeYap(Taksit taksit, BigDecimal odemeTutari, OdemeTuru odemeTuru);
}
