package com.kolayvergi.service;

import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.AracBilgisi;

import java.util.UUID;

public interface AracBilgisiService {
    AracBilgisi createAracBilgisiForAlisveris(AracBilgisiCreateRequest request);
    void updateAracBilgisi(AracBilgisiUpdateRequest request, AracBilgisi entity);
    AracBilgisiResponse getAracBilgisiById(UUID id);
}
