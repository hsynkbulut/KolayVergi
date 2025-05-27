package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.AlisverisService;
import com.kolayvergi.service.AracBilgisiService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.OdemePlaniService;
import com.kolayvergi.service.vergi.VergiHesaplamaService;
import com.kolayvergi.service.vergi.VergiHesaplamaSonuc;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final KullaniciService kullaniciService;
    private final AlisverisMapper alisverisMapper;
    private final AracBilgisiService aracBilgisiService;
    private final OdemePlaniService odemePlaniService;
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
    public AlisverisResponse getAlisveris(UUID id) {
        Alisveris alisveris = alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alışveriş bulunamadı: " + id));
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Override
    @Transactional()
    public AlisverisResponse updateAlisveris(UUID id, AlisverisCreateRequest request) {
        deleteAlisveris(id);
        return createAlisveris(request);
    }

    @Transactional()
    @Override
    public void deleteAlisveris(UUID id) {
        if (!alisverisRepository.existsById(id)) {
            throw new EntityNotFoundException("Silinecek alışveriş bulunamadı: " + id);
        }
        alisverisRepository.deleteById(id);
    }

    @Override
    public List<AlisverisResponse> getCurrentUserAlisverisler() {
        Kullanici kullanici = kullaniciService.getCurrentUser();
        List<Alisveris> alisverisler = alisverisRepository.findAllByKullaniciId(kullanici.getId());
        return alisverisler.stream()
                .map(alisverisMapper::alisverisToAlisverisResponse)
                .collect(Collectors.toList());
    }
}
