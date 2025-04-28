package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.AracBilgisiController;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.service.AracBilgisiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AracBilgisiControllerImpl implements AracBilgisiController {

    private final AracBilgisiService aracBilgisiService;

    @Override
    public ResponseEntity<AracBilgisiResponse> updateAracBilgisi(AracBilgisiUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aracBilgisiService.updateAracBilgisi(request));
    }

    @Override
    public ResponseEntity<AracBilgisiResponse> getAracBilgisiById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(aracBilgisiService.getAracBilgisiById(id));
    }

    @Override
    public ResponseEntity<AracBilgisiResponse> getAracBilgisiByAlisverisId(Long alisverisId) {
        return ResponseEntity.status(HttpStatus.OK).body(aracBilgisiService.getAracBilgisiByAlisverisId(alisverisId));
    }
}
