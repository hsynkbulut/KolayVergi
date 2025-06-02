package com.kolayvergi.service.impl;

import com.kolayvergi.constant.AracBilgisiConstants;
import com.kolayvergi.dto.mapper.AracBilgisiMapper;
import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.repository.AracBilgisiRepository;
import com.kolayvergi.service.AracBilgisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AracBilgisiServiceImpl implements AracBilgisiService {

    private final AracBilgisiRepository aracBilgisiRepository;
    private final AracBilgisiMapper aracBilgisiMapper;

    @Override
    @Transactional
    public AracBilgisi createAracBilgisiForAlisveris(AracBilgisiCreateRequest request) {
        return aracBilgisiRepository.save(aracBilgisiMapper.AracBilgisiCreateRequestToAracBilgisi(request));
    }

    @Override
    @Transactional
    public AracBilgisiResponse updateAracBilgisi(AracBilgisiUpdateRequest request) {
        AracBilgisi aracBilgisi = aracBilgisiRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(AracBilgisiConstants.ARAC_BILGISI_BULUNAMADI, request.getId())));

        aracBilgisiMapper.updateAracBilgisiFromAracBilgisiUpdateRequest(request, aracBilgisi);
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(aracBilgisiRepository.save(aracBilgisi));
    }

    @Override
    public AracBilgisiResponse getAracBilgisiById(UUID id) {
        AracBilgisi arac = aracBilgisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AracBilgisiConstants.ARAC_BILGISI_BULUNAMADI, id)));
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(arac);
    }
}
