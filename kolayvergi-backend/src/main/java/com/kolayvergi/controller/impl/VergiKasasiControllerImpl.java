package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.VergiKasasiController;
import com.kolayvergi.dto.request.VergiKasasiCreateRequest;
import com.kolayvergi.dto.request.VergiKasasiUpdateRequest;
import com.kolayvergi.dto.response.VergiKasasiResponse;
import com.kolayvergi.service.VergiKasasiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VergiKasasiControllerImpl implements VergiKasasiController {

    private final VergiKasasiService vergiKasasiService;

    @Override
    public ResponseEntity<VergiKasasiResponse> createVergiKasasi(VergiKasasiCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vergiKasasiService.createVergiKasasi(request));
    }

    @Override
    public ResponseEntity<VergiKasasiResponse> getVergiKasasiById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vergiKasasiService.getVergiKasasi(id));
    }

    @Override
    public ResponseEntity<VergiKasasiResponse> updateVergiKasasi(Long id, VergiKasasiUpdateRequest vergiKasasiUpdateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vergiKasasiService.updateVergiKasasi(id, vergiKasasiUpdateRequest));
    }

}
