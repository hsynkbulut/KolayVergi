package com.kolayvergi.service.impl;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.repository.OdemePlaniRepository;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional()
@Service
@RequiredArgsConstructor
public class OdemePlaniServiceImpl implements OdemePlaniService {

    private final TaksitService taksitService;
    private final OdemePlaniRepository odemePlaniRepository;
    private final KullaniciService kullaniciService;


    @Override
    public OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, BigDecimal vergiTutari) {

        Long kullaniciId = kullaniciService.getCurrentUser().getId();
        BigDecimal odenecekTutar = vergiTutari.add(alisveris.getTutar());

        OdemePlani odemePlani = new OdemePlani();
        odemePlani.setAlisveris(alisveris);
        odemePlani.setToplamOdenecekTutar(odenecekTutar);
        odemePlani.setToplamOdenmisTutar(BigDecimal.ZERO);
        odemePlani.setToplamTaksitSayisi(alisveris.getTaksitSayisi());
        odemePlani.setKalanTaksitSayisi(alisveris.getTaksitSayisi());
        OdemePlani dbOdeme = odemePlaniRepository.save(odemePlani);

        List<Taksit> taksitler = taksitService.createInitialTaksitler(kullaniciId, dbOdeme);
        return odemePlani;
    }

    @Override
    public OdemePlani updateOdemePlaniAfterPayment(Taksit taksit, BigDecimal guncellenmisTutar) {
        OdemePlani odemePlani = taksit.getOdemePlani();

        BigDecimal yeniToplamOdenmis = odemePlani.getToplamOdenmisTutar()
                .add(guncellenmisTutar);
        odemePlani.setToplamOdenmisTutar(yeniToplamOdenmis);

        int yeniKalanTaksitSayisi = odemePlani.getKalanTaksitSayisi() - 1;
        odemePlani.setKalanTaksitSayisi(Math.max(yeniKalanTaksitSayisi, 0));

        return odemePlaniRepository.save(odemePlani);
    }

}
