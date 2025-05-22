package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.factory.VergiTuruBelirleyici;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.AlisverisService;
import com.kolayvergi.service.AracBilgisiService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.vergi.OtvVergisiService;
import com.kolayvergi.service.vergi.KdvVergisiService;
import com.kolayvergi.service.vergi.MtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final KullaniciService kullaniciService;
    private final AlisverisMapper alisverisMapper;
    private final AracBilgisiService aracBilgisiService;
    private final VergiTuruBelirleyici vergiTuruBelirleyici;
    private final KdvVergisiService kdvVergisiService;
    private final MtvVergisiService mtvVergisiService;
    private final OtvVergisiService otvVergisiService;
    private final OdemePlaniService odemePlaniService;


    @Transactional()
    @Override
    public AlisverisResponse createAlisveris(AlisverisCreateRequest request) {

        Kullanici kullanici = kullaniciService.getCurrentUser();
        Alisveris alisveris = alisverisMapper.aliverisCreateRequestToAlisveris(request);
        alisveris.setKullanici(kullanici);
        Alisveris dbAlisveris = alisverisRepository.save(alisveris);

        if (request.getUrunTuru() == UrunTuru.OTOMOBIL) {
            if (request.getAracBilgisi() == null) {
                throw new IllegalArgumentException("Otomobil kategorisinde araç bilgisi zorunludur.");
            }
            AracBilgisi aracBilgisi = aracBilgisiService.createAracBilgisiForAlisveris(dbAlisveris, request.getAracBilgisi());
            dbAlisveris.setAracBilgisi(aracBilgisi);

            // Alisverisin tekrar kaydedilmesi
            dbAlisveris = alisverisRepository.save(dbAlisveris);
        }

        // Vergi türlerini belirle
        List<VergiTuru> vergiTurleri = vergiTuruBelirleyici.getVergiTurleri(dbAlisveris.getUrunTuru());

        // Vergileri hesapla ve kaydet
        BigDecimal toplamVergiTutari = BigDecimal.ZERO;
        OtvVergisi otvVergisi = null;

        // Önce ÖTV hesapla
        if (vergiTurleri.contains(VergiTuru.OTV)) {
            otvVergisi = otvVergisiService.createOtvVergisi(dbAlisveris, kullanici);
            toplamVergiTutari = toplamVergiTutari.add(otvVergisi.getTutar());
        }

        // Sonra KDV hesapla (ÖTV'li tutar üzerinden)
        if (vergiTurleri.contains(VergiTuru.KDV)) {
            KdvVergisi kdvVergisi = kdvVergisiService.createKdvVergisi(dbAlisveris, kullanici, otvVergisi);
            toplamVergiTutari = toplamVergiTutari.add(kdvVergisi.getTutar());
        }

        // En son MTV hesapla
        if (vergiTurleri.contains(VergiTuru.MTV)) {
            MtvVergisi mtvVergisi = mtvVergisiService.createMtvVergisi(dbAlisveris, kullanici);
            toplamVergiTutari = toplamVergiTutari.add(mtvVergisi.getTutar());
        }

        odemePlaniService.createOdemePlaniForAlisveris(alisveris, toplamVergiTutari);

        return alisverisMapper.alisverisToAlisverisResponse(dbAlisveris);
    }

    @Override
    public AlisverisResponse getAlisveris(Long id){
        Alisveris alisveris = getAlisverisById(id);
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Override
    @Transactional()
    public AlisverisResponse updateAlisveris(Long id, AlisverisCreateRequest request) {
        deleteAlisveris(id);
        return createAlisveris(request);
    }


    @Transactional()
    @Override
    public void deleteAlisveris(Long id) {
        if (!alisverisRepository.existsById(id)) {
            throw new EntityNotFoundException("Silinecek alışveriş bulunamadı: " + id);
        }
        alisverisRepository.deleteById(id);
    }

    public Alisveris getAlisverisById(Long id){
        return alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alışveriş bulunamadı: " + id));
    }

}
