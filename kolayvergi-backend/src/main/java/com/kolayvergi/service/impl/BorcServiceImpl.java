package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.BorcMapper;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.Borc;
import com.kolayvergi.repository.BorcRepository;
import com.kolayvergi.service.BorcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class BorcServiceImpl implements BorcService {

    private BorcRepository borcRepository;
    private BorcMapper borcMapper;

    public BorcServiceImpl(BorcRepository borcRepository, BorcMapper borcMapper) {
        this.borcRepository = borcRepository;
        this.borcMapper = borcMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public BorcResponse createBorc(BorcCreateRequest request) {
        Borc borc = borcMapper.borcCreateRequestToBorc(request);
        borc.setKullaniciId(request.getKullaniciId());

        Borc dbBorc = borcRepository.save(borc);

        return borcMapper.borcToBorcResponse(dbBorc);
    }

    @Override
    public BorcResponse getBorc(Long id) {
        Borc borc = getBorcById(id);
        return borcMapper.borcToBorcResponse(borc);
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
