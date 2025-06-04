package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.BorcMapper;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.Borc;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.BorcRepository;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BorcServiceImpl implements BorcService {

    private final BorcRepository borcRepository;
    private final BorcMapper borcMapper;
    private final KullaniciService kullaniciService;
    private final MessageSource messageSource;

    @Transactional()
    @Override
    public BorcResponse createBorc(BorcCreateRequest request) {
        Kullanici kullanici = kullaniciService.getCurrentUser();
        Borc borc = borcMapper.borcCreateRequestToBorc(request);
        borc.setKullanici(kullanici);
        return borcMapper.borcToBorcResponse(borcRepository.save(borc));
    }

    @Override
    public BorcResponse getBorc() {
        return getBorcByKullaniciId(kullaniciService.getCurrentUser().getId());
    }

    @Override
    public BorcResponse getBorcByKullaniciId(UUID kullaniciId) {
        return borcRepository.getBorcByKullaniciId(kullaniciId)
                .map(borcMapper::borcToBorcResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("alisveris.borc_bulunamadi_kullanici_id", new Object[]{kullaniciId}, LocaleContextHolder.getLocale())));
    }

    public Optional<BorcResponse> getBorcByKullaniciIdSafely(UUID kullaniciId) {
        return borcRepository.getBorcByKullaniciId(kullaniciId)
                .map(borcMapper::borcToBorcResponse);
    }

    @Transactional()
    @Override
    public BorcResponse updateBorc(UUID id, BorcUpdateRequest updateBorcRequest) {
        Borc borc = getBorcById(id);
        Optional.ofNullable(updateBorcRequest.getToplamBorc()).ifPresent(borc::setToplamBorc);
        Optional.ofNullable(updateBorcRequest.getKalanBorc()).ifPresent(borc::setKalanBorc);
        return borcMapper.borcToBorcResponse(borcRepository.save(borc));
    }

    @Transactional
    @Override
    public void deleteBorcByKullaniciId(UUID kullaniciId) {
        borcRepository.deleteByKullaniciId(kullaniciId);
    }

    private Borc getBorcById(UUID id) {
        return borcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("alisveris.borc_bulunamadi_borc_id", new Object[]{id}, LocaleContextHolder.getLocale())));
    }
}
