package com.kolayvergi.odemeYontemi;

import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OdemeYontemi {
    OdemeSonucu hesaplaVeOde(Taksit taksit, OdemeTuru odemeTuru, LocalDate odemeTarihi, BigDecimal kullaniciOdemeTutari);
    OdemeSonucu sadeceHesapla(Taksit taksit, LocalDate odemeTarihi);
}
