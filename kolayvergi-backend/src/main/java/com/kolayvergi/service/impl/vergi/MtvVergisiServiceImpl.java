package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.MtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.repository.MtvVergisiRepository;
import com.kolayvergi.service.vergi.MtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MtvVergisiServiceImpl implements MtvVergisiService {

    private final MtvVergisiRepository mtvVergisiRepository;
    private final MtvVergisiMapper mtvVergisiMapper;

    @Override
    @Transactional
    public MtvVergisiResponse createMtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        // 1. MTV stratejisini al ve vergi tutarını hesapla

        // 2. Alışverişten AracBilgisi çek
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalStateException("Otomobil ürün kategorisi için araç bilgisi zorunludur.");
        }

        // 3. MTV vergisi nesnesini oluştur
        MtvVergisi mtv = new MtvVergisi();
//        mtv.setFiyat(fiyat);
        mtv.setAlisveris(alisveris);
        mtv.setAracTipi(aracBilgisi.getAracTipi());
        mtv.setAracYasi(aracBilgisi.getAracYasi());
        mtv.setMotorSilindirHacmi(aracBilgisi.getMotorSilindirHacmi());
        mtv.setIlkTescilYili(aracBilgisi.getIlkTescilYili());

        MtvVergisi kayit = mtvVergisiRepository.save(mtv);
        return mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(kayit);
    }

    @Override
    public List<MtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<MtvVergisiResponse> list = new ArrayList<>();
        for (MtvVergisi mtvVergisi : mtvVergisiRepository.findByAlisverisId(alisverisId)) {
            MtvVergisiResponse mtvVergisiResponse = mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(mtvVergisi);
            list.add(mtvVergisiResponse);
        }
        return list;
    }

//    @Override
//    public List<MtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
//        return mtvVergisiRepository.findByAlisverisId(alisverisId).stream()
//                .map(mtvVergisiMapper::mtvVergisiToMtvVergisiResponse)
//                .toList();
//    }

    @Override
    public MtvVergisiResponse getMtvVergisiById(Long id) {
        MtvVergisi vergi = mtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MTV Vergisi bulunamadı: " + id));
        return mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(vergi);
    }
}
