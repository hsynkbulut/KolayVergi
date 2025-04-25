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
    public OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, int taksitSayisi, List<OdemeTuru> odemeTurleri) {
        // 1. Vergileri hesapla
        //BigDecimal toplamFiyat = vergiHesaplamaService.hesaplaToplamVergi(alisveris); + alisveris.tutar

        // 2. OdemePlani oluştur
        OdemePlani odemePlani = new OdemePlani();
        odemePlani.setAlisveris(alisveris);
        odemePlani.setToplamOdenecekTutar(new BigDecimal(100)); //Sonra buraya toplamFiyat yazilacak = vergi + tutar
        odemePlani.setToplamOdenmisTutar(BigDecimal.ZERO);
        odemePlani.setToplamTaksitSayisi(taksitSayisi);
        odemePlani.setKalanTaksitSayisi(taksitSayisi);

        // 3. Taksitleri oluştur
        List<Taksit> taksitler = taksitService.createInitialTaksitler(odemePlani, odemeTurleri);
        odemePlani.setTaksitler(taksitler);
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
