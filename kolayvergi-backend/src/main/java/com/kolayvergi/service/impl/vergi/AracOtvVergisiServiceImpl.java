package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.AracOtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.hesaplayici.AracOtvVergisiHesaplayici;
import com.kolayvergi.repository.AracOtvVergisiRepository;
import com.kolayvergi.service.vergi.AracOtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AracOtvVergisiServiceImpl implements AracOtvVergisiService {

    private final AracOtvVergisiRepository aracOtvVergisiRepository;
    private final AracOtvVergisiMapper aracOtvVergisiMapper;
    private final AracOtvVergisiHesaplayici aracOtvVergisiHesaplayici;

    @Override
    @Transactional
    public OtvVergisi createAracOtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalStateException("Otomobil ürün kategorisi için araç bilgisi zorunludur.");
        }

        OtvVergisi otvVergisi = aracOtvVergisiHesaplayici.hesapla(alisveris, kullanici);
        return aracOtvVergisiRepository.save(otvVergisi);
    }

    @Override
    public List<AracOtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<AracOtvVergisiResponse> list = new ArrayList<>();
        for (OtvVergisi otvVergisi : aracOtvVergisiRepository.findByAlisverisId(alisverisId)) {
            AracOtvVergisiResponse aracOtvVergisiResponse = aracOtvVergisiMapper.aracOtvVergisiToAracOtvVergisiResponse(otvVergisi);
            list.add(aracOtvVergisiResponse);
        }
        return list;
    }

    @Override
    public AracOtvVergisiResponse getAracOtvVergisiById(Long id) {
        OtvVergisi vergi = aracOtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araç ÖTV Vergisi bulunamadı: " + id));
        return aracOtvVergisiMapper.aracOtvVergisiToAracOtvVergisiResponse(vergi);
    }
}
