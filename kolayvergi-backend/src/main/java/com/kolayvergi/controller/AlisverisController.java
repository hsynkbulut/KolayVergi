package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ALISVERISLER) // Örneğin: /api/alisveris
public interface AlisverisController {

    @PostMapping
    ResponseEntity<AlisverisResponse> createAlisveris(@RequestBody @Valid AlisverisCreateRequest request);

    @GetMapping("/{id}")
    ResponseEntity<AlisverisResponse> getAlisverisById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<AlisverisResponse> updateAlisveris(@PathVariable("id") Long id,
                                                      @RequestBody @Valid AlisverisUpdateRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAlisveris(@PathVariable("id") Long id);
}