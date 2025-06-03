package com.kolayvergi.service.impl;

import com.kolayvergi.constant.OdemeConstants;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.generator.TaksitNoGenerator;
import com.kolayvergi.repository.TaksitRepository;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TaksitServiceImpl implements TaksitService {

    private final TaksitRepository taksitRepository;
    private final TaksitNoGenerator taksitNoGenerator;
    private final BorcService borcService;
    private final KullaniciService kullaniciService;

    @Override
    @Transactional
    public List<Taksit> createInitialTaksitler(UUID kullaniciId, OdemePlani odemePlani) {
        int taksitSayisi = odemePlani.getToplamTaksitSayisi();
        BigDecimal toplamTutar = odemePlani.getToplamOdenecekTutar();

        BigDecimal taksitTutari = toplamTutar.divide(
                BigDecimal.valueOf(taksitSayisi),
                2, 
                RoundingMode.HALF_UP
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
            taksit.setOdemeTuru(OdemeTuru.NAKIT); 
            taksitler.add(taksit);
        }

        Optional<BorcResponse> optionalBorc = borcService.getBorcByKullaniciIdSafely(kullaniciId);

        if (optionalBorc.isPresent()) {
            BorcResponse existingBorc = optionalBorc.get();

            BorcUpdateRequest updateRequest = new BorcUpdateRequest();
            updateRequest.setKullaniciId(kullaniciId);
            updateRequest.setToplamBorc(odemePlani.getToplamOdenecekTutar().add(existingBorc.getToplamBorc()));
            updateRequest.setKalanBorc(odemePlani.getToplamOdenecekTutar().add(existingBorc.getKalanBorc()));

            borcService.updateBorc(existingBorc.getId(), updateRequest);
        } else {
            BorcCreateRequest createRequest = new BorcCreateRequest();
            createRequest.setKullaniciId(kullaniciId);
            createRequest.setToplamBorc(odemePlani.getToplamOdenecekTutar());
            createRequest.setKalanBorc(odemePlani.getToplamOdenecekTutar());

            borcService.createBorc(createRequest);
        }

        return taksitler;
    }

    @Override
    public Taksit getTaksitByTaksitNo(String taksitNo) {
        return taksitRepository.findByTaksitNo(taksitNo)
                .orElseThrow(() -> new RuntimeException(String.format(OdemeConstants.TAKSIT_BULUNAMADI, taksitNo)));
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

    @Override
    public List<Taksit> getAllTaksitler() {
        return taksitRepository.findAll();
    }

    @Override
    public List<Taksit> getCurrentUserTaksitler() {
        UUID currentUserId = kullaniciService.getCurrentUser().getId();
        return taksitRepository.findByOdemePlani_Alisveris_KullaniciId(currentUserId);
    }
}
