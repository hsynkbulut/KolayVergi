package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.VergiKasasiMapper;
import com.kolayvergi.dto.request.VergiKasasiCreateRequest;
import com.kolayvergi.dto.request.VergiKasasiUpdateRequest;
import com.kolayvergi.dto.response.VergiKasasiResponse;
import com.kolayvergi.entity.VergiKasasi;
import com.kolayvergi.repository.VergiKasasiRepository;
import com.kolayvergi.service.VergiKasasiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class VergiKasasiServiceImpl implements VergiKasasiService {

    private final VergiKasasiRepository vergiKasasiRepository;
    private final VergiKasasiMapper vergiKasasiMapper;

    public VergiKasasiServiceImpl(VergiKasasiRepository vergiKasasiRepository, VergiKasasiMapper vergiKasasiMapper) {
        this.vergiKasasiRepository = vergiKasasiRepository;
        this.vergiKasasiMapper = vergiKasasiMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public VergiKasasiResponse createVergiKasasi(VergiKasasiCreateRequest request) {
        VergiKasasi vergiKasasi = vergiKasasiMapper.vergiKasasiCreateRequestToVergiKasasi(request);
        VergiKasasi dbVergiKasasi = vergiKasasiRepository.save(vergiKasasi);

        return vergiKasasiMapper.vergiKasasiToVergiKasasiResponse(dbVergiKasasi);
    }

    @Override
    public VergiKasasiResponse getVergiKasasi(Long id) {
        VergiKasasi vergiKasasi = getVergiKasasiById(id);
        return vergiKasasiMapper.vergiKasasiToVergiKasasiResponse(vergiKasasi);
    }

    @Transactional(readOnly = false)
    @Override
    public VergiKasasiResponse updateVergiKasasi(Long id, VergiKasasiUpdateRequest updateVergiKasasiRequest) {
        VergiKasasi borc = getVergiKasasiById(id);
        Optional.ofNullable(updateVergiKasasiRequest.getVergiTuru()).ifPresent(borc::setVergiTuru);
        Optional.ofNullable(updateVergiKasasiRequest.getToplamBakiye()).ifPresent(borc::setToplamBakiye);
        VergiKasasi updatedVergiKasasi = vergiKasasiRepository.save(borc);

        return vergiKasasiMapper.vergiKasasiToVergiKasasiResponse(updatedVergiKasasi);
    }

    private VergiKasasi getVergiKasasiById(Long id){
        return vergiKasasiRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
