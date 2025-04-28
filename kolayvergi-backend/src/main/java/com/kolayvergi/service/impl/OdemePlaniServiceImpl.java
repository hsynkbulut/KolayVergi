package com.kolayvergi.service.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.repository.OdemePlaniRepository;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OdemePlaniServiceImpl implements OdemePlaniService {

    //private final VergiHesaplamaService vergiHesaplamaService;
    private final TaksitService taksitService;
    private final OdemePlaniRepository odemePlaniRepository;


    @Override
    @Transactional
    public OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, BigDecimal vergiTutari) {

        BigDecimal odenecekTutar = vergiTutari.add(alisveris.getTutar());

        // 1. OdemePlani oluştur
        OdemePlani odemePlani = new OdemePlani();
        odemePlani.setAlisveris(alisveris);
        odemePlani.setToplamOdenecekTutar(odenecekTutar);
        odemePlani.setToplamOdenmisTutar(BigDecimal.ZERO);
        odemePlani.setToplamTaksitSayisi(alisveris.getTaksitSayisi());
        odemePlani.setKalanTaksitSayisi(alisveris.getTaksitSayisi());
        odemePlaniRepository.save(odemePlani);

        // 2. Taksitleri oluştur
        List<Taksit> taksitler = taksitService.createInitialTaksitler(odemePlani);
        return odemePlani;
    }


    @Override
    @Transactional
    public OdemePlani updateOdemePlaniAfterPayment(Taksit taksit) {
        OdemePlani odemePlani = taksit.getOdemePlani();

        BigDecimal yeniToplamOdenmis = odemePlani.getToplamOdenmisTutar()
                .add(taksit.getTaksitTutari());
        odemePlani.setToplamOdenmisTutar(yeniToplamOdenmis);

        int yeniKalanTaksitSayisi = odemePlani.getKalanTaksitSayisi() - 1;
        odemePlani.setKalanTaksitSayisi(Math.max(yeniKalanTaksitSayisi, 0));

        OdemePlani updatedOdemePlani = odemePlaniRepository.save(odemePlani);

        return updatedOdemePlani;
    }
}
