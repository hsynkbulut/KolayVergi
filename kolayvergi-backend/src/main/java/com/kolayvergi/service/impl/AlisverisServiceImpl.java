package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.factory.VergiTuruBelirleyici;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.AlisverisService;
import com.kolayvergi.service.AracBilgisiService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.vergi.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final KullaniciService kullaniciService;
    private final AlisverisMapper alisverisMapper;
    private final AracBilgisiService aracBilgisiService;
    private final OdemePlaniService odemePlaniService;
    private final KdvVergisiService kdvVergisiService;
    private final OtvVergisiService otvVergisiService;
    private final MtvVergisiService mtvVergisiService;
    private final VergiTuruBelirleyici vergiTuruBelirleyici;
    private final VergiHesaplamaService vergiHesaplamaService;

    @Override
    @Transactional
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

        // Vergileri hesapla
        VergiHesaplamaSonuc sonuc = vergiHesaplamaService.hesaplaVergiler(dbAlisveris, kullanici);
        
        // Ödeme planını oluştur
        odemePlaniService.createOdemePlaniForAlisveris(dbAlisveris, sonuc.getToplamVergiTutari());

        return alisverisMapper.alisverisToAlisverisResponse(dbAlisveris);
    }

    @Override
    public AlisverisResponse getAlisveris(Long id) {
        Alisveris alisveris = alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alışveriş bulunamadı: " + id));
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Override
    @Transactional()
    public AlisverisResponse updateAlisveris(Long id, AlisverisCreateRequest request) {
        deleteAlisveris(id);
        return createAlisveris(request);
    }

    @Override
    public void deleteAlisveris(Long id) {
        if (!alisverisRepository.existsById(id)) {
            throw new EntityNotFoundException("Alışveriş bulunamadı: " + id);
        }
        alisverisRepository.deleteById(id);
    }
}
