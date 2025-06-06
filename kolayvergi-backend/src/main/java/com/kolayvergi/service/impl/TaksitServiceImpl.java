package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.BorcMapper;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.dto.mapper.TaksitMapper;
import com.kolayvergi.dto.response.TaksitResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.generator.TaksitNoGenerator;
import com.kolayvergi.repository.TaksitRepository;
import com.kolayvergi.repository.AlisverisRepository;
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
import com.kolayvergi.dto.BorcDurumu;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TaksitServiceImpl implements TaksitService {

    private final TaksitRepository taksitRepository;
    private final TaksitNoGenerator taksitNoGenerator;
    private final BorcService borcService;
    private final KullaniciService kullaniciService;
    private final MessageSource messageSource;
    private final TaksitMapper taksitMapper;
    private final AlisverisRepository alisverisRepository;
    private final BorcMapper borcMapper;

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
    public List<TaksitResponse> getCurrentUserTaksitler() {
        UUID currentUserId = kullaniciService.getCurrentUser().getId();
        List<Taksit> taksitler = taksitRepository.findByOdemePlani_Alisveris_KullaniciId(currentUserId);
        return taksitMapper.taksitListToTaksitResponseList(taksitler);
    }

    @Override
    @Transactional
    public List<Taksit> updateTaksitler(Alisveris alisveris, OdemePlani odemePlani) {
        int yeniTaksitSayisi = alisveris.getTaksitSayisi();
        BigDecimal toplamTutar = odemePlani.getToplamOdenecekTutar();
        BigDecimal yeniTaksitTutari = toplamTutar.divide(BigDecimal.valueOf(yeniTaksitSayisi), 2, RoundingMode.HALF_UP);
        List<Taksit> taksitler = odemePlani.getTaksitler();

        updateTaksitList(taksitler, yeniTaksitSayisi, yeniTaksitTutari, odemePlani, alisveris.getKullanici().getId());

        UUID kullaniciId = alisveris.getKullanici().getId();
        List<Alisveris> kullanicininAlisverisleri = alisverisRepository.findAllByKullaniciId(kullaniciId);

        BorcDurumu borcDurumu = hesaplaBorcDurumu(kullaniciId, kullanicininAlisverisleri);

        Optional<BorcResponse> optionalBorc = borcService.getBorcByKullaniciIdSafely(kullaniciId);
        if (optionalBorc.isPresent()) {
            BorcResponse existingBorc = optionalBorc.get();
            BorcUpdateRequest updateRequest = borcMapper.borcDurumutoBorcUpdateRequest(borcDurumu);
            borcService.updateBorc(existingBorc.getId(), updateRequest);
        }
        return taksitler;
    }

    private void updateTaksitList(List<Taksit> taksitler, int yeniTaksitSayisi, BigDecimal yeniTaksitTutari, OdemePlani odemePlani, UUID kullaniciId) {
        if (taksitler.size() < yeniTaksitSayisi) {
            for (int i = taksitler.size(); i < yeniTaksitSayisi; i++) {
                taksitler.add(yeniTaksitOlustur(odemePlani, kullaniciId, i + 1, yeniTaksitTutari));
            }
        } else if (taksitler.size() > yeniTaksitSayisi) {
            taksitler.subList(yeniTaksitSayisi, taksitler.size()).clear();
        }
        for (Taksit taksit : taksitler) {
            taksit.setTaksitTutari(yeniTaksitTutari);
            taksit.setOdemePlani(odemePlani);
        }
    }

    private Taksit yeniTaksitOlustur(OdemePlani odemePlani, UUID kullaniciId, int index, BigDecimal taksitTutari) {
        Taksit taksit = new Taksit();
        taksit.setOdemePlani(odemePlani);
        taksit.setTaksitNo(taksitNoGenerator.generateTaksitNo(kullaniciId, index));
        taksit.setTaksitTutari(taksitTutari);
        taksit.setSonOdemeTarihi(LocalDate.now().plusMonths(index));
        taksit.setOdemeTarihi(null);
        taksit.setDurum(OdemeDurumu.ODENMEDI);
        taksit.setOdemeTuru(OdemeTuru.NAKIT);
        return taksit;
    }

    private BorcDurumu hesaplaBorcDurumu(UUID kullaniciId, List<Alisveris> kullanicininAlisverisleri) {
        BigDecimal toplamBorc = BigDecimal.ZERO;
        BigDecimal odenenBorc = BigDecimal.ZERO;
        for (Alisveris kullaniciAlisverisi : kullanicininAlisverisleri) {
            OdemePlani alisverisOdemePlani = kullaniciAlisverisi.getOdemePlani();
            if (alisverisOdemePlani != null && alisverisOdemePlani.getTaksitler() != null) {
                for (Taksit taksit : alisverisOdemePlani.getTaksitler()) {
                    toplamBorc = toplamBorc.add(taksit.getTaksitTutari());
                    if (taksit.getDurum() == OdemeDurumu.ODENDI) {
                        odenenBorc = odenenBorc.add(taksit.getTaksitTutari());
                    }
                }
            }
        }
        return new BorcDurumu(kullaniciId, toplamBorc, toplamBorc.subtract(odenenBorc));
    }
}
