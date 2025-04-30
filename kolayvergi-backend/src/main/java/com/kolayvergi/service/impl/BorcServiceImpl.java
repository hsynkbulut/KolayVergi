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

    @Transactional(readOnly = false)
    @Override
    public BorcResponse createBorc(BorcCreateRequest request) {
        Kullanici kullanici = kullaniciService.getKullanici(request.getKullaniciId());
        Borc borc = borcMapper.borcCreateRequestToBorc(request);
        borc.setKullanici(kullanici);

        Borc dbBorc = borcRepository.save(borc);
        return borcMapper.borcToBorcResponse(dbBorc);
    }

    @Override
    public BorcResponse getBorc(Long id) {
        Borc borc = getBorcById(id);
        return borcMapper.borcToBorcResponse(borc);
    }

    public BorcResponse getBorcByKullaniciId(Long kullaniciId) {
        Optional<Borc> borcOpt = borcRepository.getBorcByKullanici_Id(kullaniciId);
        if (borcOpt.isEmpty()) {
            return null;
        }
        return borcMapper.borcToBorcResponse(borcOpt.get());
    }


    @Transactional(readOnly = false)
    @Override
    public BorcResponse updateBorc(Long id, BorcUpdateRequest updateBorcRequest) {
        Borc borc = getBorcById(id);
        Optional.ofNullable(updateBorcRequest.getToplamBorc()).ifPresent(borc::setToplamBorc);
        Optional.ofNullable(updateBorcRequest.getKalanBorc()).ifPresent(borc::setKalanBorc);
        Borc updatedBorc = borcRepository.save(borc);

        return borcMapper.borcToBorcResponse(updatedBorc);
    }

    private Borc getBorcById(Long id){
        return borcRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
