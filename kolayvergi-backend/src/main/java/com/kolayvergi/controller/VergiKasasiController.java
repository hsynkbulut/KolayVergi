package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.VergiKasasiCreateRequest;
import com.kolayvergi.dto.request.VergiKasasiUpdateRequest;
import com.kolayvergi.dto.response.VergiKasasiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = ApiConstants.VERGIKASASI)
public interface VergiKasasiController {

    @PostMapping
    ResponseEntity<VergiKasasiResponse> createVergiKasasi(@RequestBody @Valid VergiKasasiCreateRequest request);

    @GetMapping(path = ApiConstants.ID)
    ResponseEntity<VergiKasasiResponse> getVergiKasasiById(@PathVariable(name = "id") Long id);

    @PutMapping(path = ApiConstants.ID)
    ResponseEntity<VergiKasasiResponse> updateVergiKasasi(@PathVariable(name = "id") Long id,
                                            @RequestBody VergiKasasiUpdateRequest vergiKasasiUpdateRequest);
}