package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.OtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.OtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.hesaplayici.OtvVergisiHesaplayici;
import com.kolayvergi.repository.AracOtvVergisiRepository;
import com.kolayvergi.service.vergi.OtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OtvVergisiServiceImpl implements OtvVergisiService {

    private final AracOtvVergisiRepository aracOtvVergisiRepository;
    private final OtvVergisiMapper otvVergisiMapper;
    private final OtvVergisiHesaplayici otvVergisiHesaplayici;

    @Override
    @Transactional
    public OtvVergisi createOtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalStateException("Otomobil ürün kategorisi için araç bilgisi zorunludur.");
        }

        OtvVergisi otvVergisi = otvVergisiHesaplayici.hesapla(alisveris, kullanici);
        return aracOtvVergisiRepository.save(otvVergisi);
    }

    @Override
    public List<OtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<OtvVergisiResponse> list = new ArrayList<>();
        for (OtvVergisi otvVergisi : aracOtvVergisiRepository.findByAlisverisId(alisverisId)) {
            OtvVergisiResponse otvVergisiResponse = otvVergisiMapper.otvVergisiToOtvVergisiResponse(otvVergisi);
            list.add(otvVergisiResponse);
        }
        return list;
    }

    @Override
    public OtvVergisiResponse getAracOtvVergisiById(Long id) {
        OtvVergisi vergi = aracOtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araç ÖTV Vergisi bulunamadı: " + id));
        return otvVergisiMapper.otvVergisiToOtvVergisiResponse(vergi);
    }
}
