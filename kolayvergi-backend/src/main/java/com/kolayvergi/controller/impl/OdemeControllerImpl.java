package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.OdemeController;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;
import com.kolayvergi.service.OdemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OdemeControllerImpl implements OdemeController {

    private final OdemeService odemeService;

    @Override
    public ResponseEntity<OdemeSonucu> taksitOde(TaksitOdemeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(odemeService.taksitOde(request));
    }

    @Override
    public ResponseEntity<OdemeSonucu> taksitOdemeBilgisi(String taksitNo, OdemeTuru odemeTuru) {
        return ResponseEntity.ok(odemeService.taksitOdemeBilgisi(taksitNo, odemeTuru));
    }

//    @Override
//    public ResponseEntity<Boolean> taksitOde(TaksitOdemeRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(odemeService.taksitOde(request));
//    }

}
