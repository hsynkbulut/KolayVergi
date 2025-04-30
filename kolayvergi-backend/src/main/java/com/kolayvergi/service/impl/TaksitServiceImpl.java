package com.kolayvergi.service.impl;

import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.generator.TaksitNoGenerator;
import com.kolayvergi.repository.TaksitRepository;
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
    private final TaksitNoGenerator taksitNoGenerator;

    @Override
    @Transactional
    public List<Taksit> createInitialTaksitler(Long kullaniciId, OdemePlani odemePlani) {
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
            taksit.setTaksitNo(taksitNoGenerator.generateTaksitNo(kullaniciId, i + 1));
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

    @Override
    @Transactional
    public Taksit getTaksitByTaksitNo(String taksitNo) {
        return taksitRepository.findByTaksitNo(taksitNo)
                .orElseThrow(() -> new RuntimeException("Taksit bulunamadı: " + taksitNo));
    }

    @Override
    @Transactional
    public Taksit updateTaksitForPayment(Taksit taksit, OdemeTuru odemeTuru, BigDecimal guncellenmisTutar) {
        taksit.setOdemeTarihi(LocalDate.now());
        taksit.setDurum(OdemeDurumu.ODENDI);
        taksit.setOdemeTuru(odemeTuru);
        taksit.setTaksitTutari(guncellenmisTutar);
        return taksitRepository.save(taksit);
    }
}
