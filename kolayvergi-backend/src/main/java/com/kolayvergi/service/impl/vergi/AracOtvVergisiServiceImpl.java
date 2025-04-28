package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.AracOtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.AracOtvVergisi;
import com.kolayvergi.repository.AracOtvVergisiRepository;
import com.kolayvergi.service.vergi.AracOtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AracOtvVergisiServiceImpl implements AracOtvVergisiService {

    private final AracOtvVergisiRepository aracOtvVergisiRepository;
    private final AracOtvVergisiMapper aracOtvVergisiMapper;

    @Override
    @Transactional
    public AracOtvVergisiResponse createAracOtvVergisi(Alisveris alisveris, Kullanici kullanici) {

        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalStateException("Otomobil ürün kategorisi için araç bilgisi zorunludur.");
        }

        AracOtvVergisi otv = new AracOtvVergisi();
//        otv.setFiyat(fiyat);
        otv.setAlisveris(alisveris);
        otv.setAracTipi(aracBilgisi.getAracTipi());
        otv.setMotorSilindirHacmi(aracBilgisi.getMotorSilindirHacmi());

        AracOtvVergisi saved = aracOtvVergisiRepository.save(otv);
        return aracOtvVergisiMapper.aracOtvVergisiToAracOtvVergisiResponse(saved);
    }

    @Override
    public List<AracOtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<AracOtvVergisiResponse> list = new ArrayList<>();
        for (AracOtvVergisi aracOtvVergisi : aracOtvVergisiRepository.findByAlisverisId(alisverisId)) {
            AracOtvVergisiResponse aracOtvVergisiResponse = aracOtvVergisiMapper.aracOtvVergisiToAracOtvVergisiResponse(aracOtvVergisi);
            list.add(aracOtvVergisiResponse);
        }
        return list;
    }

//    @Override
//    public List<AracOtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
//        return aracOtvVergisiRepository.findByAlisverisId(alisverisId).stream()
//                .map(aracOtvVergisiMapper::aracOtvVergisiToAracOtvVergisiResponse)
//                .toList();
//    }

    @Override
    public AracOtvVergisiResponse getAracOtvVergisiById(Long id) {
        AracOtvVergisi vergi = aracOtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araç ÖTV Vergisi bulunamadı: " + id));
        return aracOtvVergisiMapper.aracOtvVergisiToAracOtvVergisiResponse(vergi);
    }
}
