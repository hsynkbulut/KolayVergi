package com.kolayvergi.service;

import com.kolayvergi.dto.request.VergiKasasiCreateRequest;
import com.kolayvergi.dto.request.VergiKasasiUpdateRequest;
import com.kolayvergi.dto.response.VergiKasasiResponse;

public interface VergiKasasiService {
    VergiKasasiResponse createVergiKasasi(VergiKasasiCreateRequest vergiKasasi);
    VergiKasasiResponse getVergiKasasi(Long id);
    VergiKasasiResponse updateVergiKasasi(Long id, VergiKasasiUpdateRequest updateVergiKasasi);
}
