package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.AlisverisController;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.service.AlisverisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlisverisControllerImpl implements AlisverisController {

    private final AlisverisService alisverisService;

    @Override
    public ResponseEntity<AlisverisResponse> createAlisveris(AlisverisCreateRequest request) {
        AlisverisResponse response = alisverisService.createAlisveris(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<AlisverisResponse> getAlisverisById(Long id) {
        AlisverisResponse response = alisverisService.getAlisveris(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AlisverisResponse> updateAlisveris(Long id, AlisverisCreateRequest request) {
        AlisverisResponse response = alisverisService.updateAlisveris(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteAlisveris(Long id) {
        alisverisService.deleteAlisveris(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
