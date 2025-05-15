package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.MtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.hesaplayici.MtvVergisiHesaplayici;
import com.kolayvergi.repository.MtvVergisiRepository;
import com.kolayvergi.service.vergi.MtvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MtvVergisiServiceImpl implements MtvVergisiService {

    private final MtvVergisiRepository mtvVergisiRepository;
    private final MtvVergisiMapper mtvVergisiMapper;
    private final MtvVergisiHesaplayici mtvVergisiHesaplayici;

    @Override
    @Transactional
    public MtvVergisi createMtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        AracBilgisi aracBilgisi = alisveris.getAracBilgisi();
        if (aracBilgisi == null) {
            throw new IllegalStateException("Otomobil ürün kategorisi için araç bilgisi zorunludur.");
        }

        MtvVergisi mtvVergisi = mtvVergisiHesaplayici.hesapla(alisveris, kullanici);
        return mtvVergisiRepository.save(mtvVergisi);
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

    @Override
    public MtvVergisiResponse getMtvVergisiById(Long id) {
        MtvVergisi vergi = mtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MTV Vergisi bulunamadı: " + id));
        return mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(vergi);
    }
}
