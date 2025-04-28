package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AracBilgisiMapper;
import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.repository.AracBilgisiRepository;
import com.kolayvergi.service.AracBilgisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AracBilgisiServiceImpl implements AracBilgisiService {

    private final AracBilgisiRepository aracBilgisiRepository;
    private final AracBilgisiMapper aracBilgisiMapper;

    @Override
    @Transactional
    public AracBilgisi createAracBilgisiForAlisveris(Alisveris alisveris, AracBilgisiCreateRequest request) {
        AracBilgisi arac = aracBilgisiMapper.AracBilgisiCreateRequestToAracBilgisi(request);
        arac.setAlisveris(alisveris);
        alisveris.setAracBilgisi(arac);
        return aracBilgisiRepository.save(arac);
    }

    @Override
    @Transactional
    public AracBilgisiResponse updateAracBilgisi(AracBilgisiUpdateRequest request) {
        AracBilgisi entity = aracBilgisiRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Araç bilgisi bulunamadı: " + request.getId()));

        aracBilgisiMapper.updateAracBilgisiFromAracBilgisiUpdateRequest(request, entity);
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(aracBilgisiRepository.save(entity));
    }

    @Override
    public AracBilgisiResponse getAracBilgisiById(Long id) {
        AracBilgisi arac = aracBilgisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araç bilgisi bulunamadı: " + id));
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(arac);
    }

    @Override
    public AracBilgisiResponse getAracBilgisiByAlisverisId(Long alisverisId) {
        AracBilgisi arac = aracBilgisiRepository.findByAlisverisId(alisverisId)
                .orElseThrow(() -> new EntityNotFoundException("Bu alışverişe ait araç bilgisi bulunamadı."));
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(arac);
    }
}
