package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.VergiHesaplamaSonucResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;

public interface VergiHesaplamaService {
    VergiHesaplamaSonucResponse hesaplaVergiler(Alisveris alisveris, Kullanici kullanici);
} 