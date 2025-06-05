package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AracBilgisiMapper;
import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.repository.AracBilgisiRepository;
import com.kolayvergi.service.AracBilgisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AracBilgisiServiceImpl implements AracBilgisiService {

    private final AracBilgisiRepository aracBilgisiRepository;
    private final AracBilgisiMapper aracBilgisiMapper;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public AracBilgisi createAracBilgisiForAlisveris(AracBilgisiCreateRequest request) {
        return aracBilgisiRepository.save(aracBilgisiMapper.aracBilgisiCreateRequestToAracBilgisi(request));
    }

    @Transactional
    public void updateAracBilgisi(AracBilgisiUpdateRequest request, AracBilgisi entity) {
        aracBilgisiMapper.updateAracBilgisiFromAracBilgisiUpdateRequest(request, entity);
        aracBilgisiRepository.save(entity);
    }

    @Override
    public AracBilgisiResponse getAracBilgisiById(UUID id) {
        AracBilgisi aracBilgisi = aracBilgisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("alisveris.arac_bilgisi_bulunamadi", new Object[]{id},
                                LocaleContextHolder.getLocale())
                ));
        return aracBilgisiMapper.aracBilgisiToAracBilgisiResponse(aracBilgisi);
    }
}
