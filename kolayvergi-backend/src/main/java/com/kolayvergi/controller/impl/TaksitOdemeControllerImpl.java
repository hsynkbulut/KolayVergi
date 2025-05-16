package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.TaksitOdemeController;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.TaksitOdemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaksitOdemeControllerImpl implements TaksitOdemeController {

    private final TaksitOdemeService taksitOdemeService;

    @Override
    public ResponseEntity<OdemeSonucu> taksitOdemeYap(TaksitOdemeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taksitOdemeService.taksitOdemeYap(request));
    }

    @Override
    public ResponseEntity<OdemeSonucu> taksitOdemeDetaylariniGetir(String taksitNo, OdemeTuru odemeTuru) {
        return ResponseEntity.ok(taksitOdemeService.taksitOdemeDetaylariniGetir(taksitNo, odemeTuru));
    }
}
