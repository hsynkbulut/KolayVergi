package com.kolayvergi.service.impl;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.Alisveris;
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
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    private final MessageSource messageSource;

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
            taksit.setSonOdemeTarihi(LocalDate.now().plusMonths(i + 1L));
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
                .orElseThrow(() -> new RuntimeException(messageSource.getMessage("odeme.taksit_bulunamadi", new Object[]{taksitNo}, LocaleContextHolder.getLocale())));
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

    @Override
    @Transactional
    public List<Taksit> updateTaksitler(Alisveris alisveris, OdemePlani odemePlani) {
        int yeniTaksitSayisi = alisveris.getTaksitSayisi();
        BigDecimal toplamTutar = odemePlani.getToplamOdenecekTutar();
        BigDecimal yeniTaksitTutari = toplamTutar.divide(BigDecimal.valueOf(yeniTaksitSayisi), 2, RoundingMode.HALF_UP);
        List<Taksit> taksitler = odemePlani.getTaksitler();

        if (taksitler.size() < yeniTaksitSayisi) {
            for (int i = taksitler.size(); i < yeniTaksitSayisi; i++) {
                Taksit yeniTaksit = new Taksit();
                yeniTaksit.setOdemePlani(odemePlani);
                yeniTaksit.setTaksitNo(taksitNoGenerator.generateTaksitNo(alisveris.getKullanici().getId(), i + 1));
                yeniTaksit.setTaksitTutari(yeniTaksitTutari);
                yeniTaksit.setSonOdemeTarihi(LocalDate.now().plusMonths(i + 1L));
                yeniTaksit.setOdemeTarihi(null);
                yeniTaksit.setDurum(OdemeDurumu.ODENMEDI);
                yeniTaksit.setOdemeTuru(OdemeTuru.NAKIT);
                taksitler.add(yeniTaksit);
            }
        } else if (taksitler.size() > yeniTaksitSayisi) {
            taksitler.subList(yeniTaksitSayisi, taksitler.size()).clear();
        }
        for (Taksit taksit : taksitler) {
            taksit.setTaksitTutari(yeniTaksitTutari);
            taksit.setOdemePlani(odemePlani);
        }
        Optional<BorcResponse> optionalBorc = borcService.getBorcByKullaniciIdSafely(alisveris.getKullanici().getId());
        if (optionalBorc.isPresent()) {
            BorcResponse existingBorc = optionalBorc.get();
            BorcUpdateRequest updateRequest = new BorcUpdateRequest();
            updateRequest.setKullaniciId(alisveris.getKullanici().getId());
            updateRequest.setToplamBorc(toplamTutar);
            updateRequest.setKalanBorc(toplamTutar);
            borcService.updateBorc(existingBorc.getId(), updateRequest);
        }
        return taksitler;
    }
}
