package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.BorcController;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.service.BorcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorcControllerImpl implements BorcController {

    private final BorcService borcService;

    public BorcControllerImpl(BorcService borcService) {
        this.borcService = borcService;
    }

    @Override
    public ResponseEntity<BorcResponse> createBorc(BorcCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borcService.createBorc(request));
    }

    @Override
    public ResponseEntity<BorcResponse> getBorcById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(borcService.getBorc(id));
    }

    @Override
    public ResponseEntity<BorcResponse> updateBorc(Long id, BorcUpdateRequest borcUpdateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(borcService.updateBorc(id, borcUpdateRequest));
    }

}
