package com.kolayvergi.service.impl;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.repository.TaksitRepository;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaksitServiceImpl implements TaksitService {

    private final TaksitRepository taksitRepository;

    @Override
    @Transactional
    public List<Taksit> createInitialTaksitler(OdemePlani odemePlani) {
        int taksitSayisi = odemePlani.getToplamTaksitSayisi();
        BigDecimal toplamTutar = odemePlani.getToplamOdenecekTutar();


        BigDecimal taksitTutari = toplamTutar.divide(
                BigDecimal.valueOf(taksitSayisi),
                2, // virgülden sonra kaç basamak olacak
                RoundingMode.HALF_UP // nasıl yuvarlanacak (en güvenli standart yöntem)
        );

        List<Taksit> taksitler = new ArrayList<>();

        for (int i = 0; i < taksitSayisi; i++) {
            Taksit taksit = new Taksit();
            taksit.setOdemePlani(odemePlani);
            //taksit.setTaksitNo(generateTaksitNo(odemePlani, i + 1));
            taksit.setTaksitNo("taksitNo-" + i+1);
            taksit.setTaksitTutari(taksitTutari);
            taksit.setSonOdemeTarihi(LocalDate.now().plusMonths(i + 1));
            taksit.setOdemeTarihi(null);
            taksit.setDurum(OdemeDurumu.ODENMEDI);
            taksit.setOdemeTuru(OdemeTuru.NAKIT); // ❗️ Her taksit için bağımsız ödeme türü atanacak ileride. Liste seklinde alinacak
            taksitler.add(taksit);
        }

        taksitRepository.saveAll(taksitler);
        return taksitler;
    }

    private String generateTaksitNo(OdemePlani odemePlani, int index) {
        String vergiTuru = odemePlani.getAlisveris().getUrunTuru().name();
        LocalDate now = LocalDate.now();
        String datePart = now.format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy_HHmm"));
        String kullaniciId = String.format("%03d", odemePlani.getAlisveris().getKullanici().getId());
        String indexStr = String.format("%03d", index);
        String randomCheck = String.format("%04d", (int) (Math.random() * 9999));
        String numerator = String.format("%04d", index);

        //GIDA_28042025_1530_005_001_4567_0001
        return vergiTuru + "_" + datePart + "_" + kullaniciId + "_" + indexStr + "_" + randomCheck + "_" + numerator;
    }

}
