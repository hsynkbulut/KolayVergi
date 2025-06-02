package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.BorcController;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.service.BorcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BorcControllerImpl implements BorcController {

    private final BorcService borcService;

    @Override
    public ResponseEntity<BorcResponse> getBorcById() {
        return ResponseEntity.status(HttpStatus.OK).body(borcService.getBorc());
    }
}
