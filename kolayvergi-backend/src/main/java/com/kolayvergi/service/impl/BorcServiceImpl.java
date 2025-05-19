package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.BorcMapper;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Borc;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.BorcRepository;
import com.kolayvergi.service.BorcService;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BorcServiceImpl implements BorcService {

    private final BorcRepository borcRepository;
    private final BorcMapper borcMapper;
    private final KullaniciService kullaniciService;

    @Transactional()
    @Override
    public BorcResponse createBorc(BorcCreateRequest request) {
        Kullanici kullanici = kullaniciService.getKullanici(request.getKullaniciId());
        Borc borc = borcMapper.borcCreateRequestToBorc(request);
        borc.setKullanici(kullanici);
        return borcMapper.borcToBorcResponse(borcRepository.save(borc));
    }

    @Override
    public BorcResponse getBorc(Long id) {
        return borcMapper.borcToBorcResponse(getBorcById(id));
    }

    @Override
    public BorcResponse getBorcByKullaniciId(Long kullaniciId) {
        return borcRepository.getBorcByKullaniciId(kullaniciId)
                .map(borcMapper::borcToBorcResponse)
                .orElseThrow(() -> new EntityNotFoundException("Borc bulunamadı. Kullanıcı ID: " + kullaniciId));
    }

    public Optional<BorcResponse> getBorcByKullaniciIdSafely(Long kullaniciId) {
        return borcRepository.getBorcByKullaniciId(kullaniciId)
                .map(borcMapper::borcToBorcResponse);
    }


    @Transactional()
    @Override
    public BorcResponse updateBorc(Long id, BorcUpdateRequest updateBorcRequest) {
        Borc borc = getBorcById(id);
        Optional.ofNullable(updateBorcRequest.getToplamBorc()).ifPresent(borc::setToplamBorc);
        Optional.ofNullable(updateBorcRequest.getKalanBorc()).ifPresent(borc::setKalanBorc);

        return borcMapper.borcToBorcResponse(borcRepository.save(borc));
    }

    private Borc getBorcById(Long id) {
        return borcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Borc bulunamadı. ID: " + id));
    }
}
