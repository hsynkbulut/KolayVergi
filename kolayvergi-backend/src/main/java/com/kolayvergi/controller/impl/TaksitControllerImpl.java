package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.TaksitController;
import com.kolayvergi.dto.response.TaksitResponse;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaksitControllerImpl implements TaksitController {

    private final TaksitService taksitService;

    @Override
    public ResponseEntity<List<TaksitResponse>> getCurrentUserTaksitler() {
        return ResponseEntity.ok(taksitService.getCurrentUserTaksitler());
    }
} 