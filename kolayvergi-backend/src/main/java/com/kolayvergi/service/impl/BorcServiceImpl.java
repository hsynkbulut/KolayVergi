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
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public BorcResponse createBorc(BorcCreateRequest request) {
        Kullanici kullanici = kullaniciService.getCurrentUser();
        Borc borc = borcMapper.borcCreateRequestToBorc(request);
        borc.setKullanici(kullanici);
        return borcMapper.borcToBorcResponse(borcRepository.save(borc));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public BorcResponse getBorc() {
        return getBorcByKullaniciId(kullaniciService.getCurrentUser().getId());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public BorcResponse getBorcByKullaniciId(UUID kullaniciId) {
        Borc borc = borcRepository.getBorcByKullaniciId(kullaniciId)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("alisveris.borc_bulunamadi_kullanici_id", new Object[]{kullaniciId}, LocaleContextHolder.getLocale())));
        return borcMapper.borcToBorcResponse(borc);
    }

    public Optional<BorcResponse> getBorcByKullaniciIdSafely(UUID kullaniciId) {
        return borcRepository.getBorcByKullaniciId(kullaniciId)
                .map(borcMapper::borcToBorcResponse);
    }

    @Transactional()
    @Override
    @PreAuthorize("hasRole('ROLE_USER') and @borcServiceImpl.isCurrentUserOwner(#id)")
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

    @Override
    public void kalanBorcuGuncelle(UUID kullaniciId, BigDecimal odemeTutari) {
        BorcResponse dbBorc = getBorcByKullaniciId(kullaniciId);
        if (ObjectUtils.isNotEmpty(dbBorc)) {
            BorcUpdateRequest update = new BorcUpdateRequest();
            update.setKullaniciId(kullaniciId);
            update.setKalanBorc(dbBorc.getKalanBorc().subtract(odemeTutari));
            updateBorc(dbBorc.getId(), update);
        }
    }

    public boolean isCurrentUserOwner(UUID borcId) {
        if (borcId == null) return false;
        Borc borc = borcRepository.findById(borcId).orElse(null);
        if (borc == null || borc.getKullanici() == null) return false;
        return kullaniciService.getCurrentUser().getId().equals(borc.getKullanici().getId());
    }
}
