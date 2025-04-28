package com.kolayvergi.factory;

import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;

import java.util.List;

public interface VergiTuruBelirleyici {
    List<VergiTuru> getVergiTurleri(UrunTuru urunTuru);
}
