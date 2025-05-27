package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.TaksitController;
import com.kolayvergi.dto.response.TaksitResponse;
import com.kolayvergi.service.TaksitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaksitControllerImpl implements TaksitController {

    private final TaksitService taksitService;

    @Override
    public ResponseEntity<List<TaksitResponse>> getCurrentUserTaksitler() {
        List<TaksitResponse> taksitler = taksitService.getCurrentUserTaksitler().stream()
                .map(taksit -> new TaksitResponse(
                        taksit.getId(),
                        taksit.getTaksitNo(),
                        taksit.getTaksitTutari(),
                        taksit.getOdemeTarihi(),
                        taksit.getSonOdemeTarihi(),
                        taksit.getDurum(),
                        taksit.getOdemeTuru(),
                        taksit.getOdemePlani().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(taksitler);
    }
} 