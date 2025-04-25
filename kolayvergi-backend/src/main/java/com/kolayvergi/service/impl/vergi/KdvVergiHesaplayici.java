package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.service.vergi.VergiHesaplayici;
import com.kolayvergi.util.vergi.KdvOranAyarlayici;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KdvVergiHesaplayici implements VergiHesaplayici {

    @Override
    public BigDecimal hesapla(Alisveris alisveris, Kullanici kullanici) {
        UrunTuru urunTuru = alisveris.getUrunTuru();
        BigDecimal temelOran = urunTuru.getOran();
        BigDecimal duzenlenmisOran = KdvOranAyarlayici.uygula(temelOran, kullanici);
        return alisveris.getTutar().multiply(duzenlenmisOran);
    }
}
