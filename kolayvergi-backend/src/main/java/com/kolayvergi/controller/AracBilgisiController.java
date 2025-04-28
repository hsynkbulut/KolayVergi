package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ARAC_BILGISI)
public interface AracBilgisiController {

    @PutMapping
    ResponseEntity<AracBilgisiResponse> updateAracBilgisi(@RequestBody @Valid AracBilgisiUpdateRequest request);

    @GetMapping(ApiConstants.ID)
    ResponseEntity<AracBilgisiResponse> getAracBilgisiById(@PathVariable(name = "id") Long id);

    @GetMapping(ApiConstants.ALISVERIS_ID)
    ResponseEntity<AracBilgisiResponse> getAracBilgisiByAlisverisId(@PathVariable(name = "alisverisId") Long alisverisId);
}
