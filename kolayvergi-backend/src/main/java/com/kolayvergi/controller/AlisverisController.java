package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ALISVERISLER)
public interface AlisverisController {

    @PostMapping
    ResponseEntity<AlisverisResponse> createAlisveris(@RequestBody @Valid AlisverisCreateRequest request);

    @GetMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> getAlisverisById(@PathVariable("id") Long id);

    @PutMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> updateAlisveris(@PathVariable("id") Long id,
                                                      @RequestBody @Valid AlisverisUpdateRequest request);
    @DeleteMapping(ApiConstants.ID)
    ResponseEntity<Void> deleteAlisveris(@PathVariable("id") Long id);
}