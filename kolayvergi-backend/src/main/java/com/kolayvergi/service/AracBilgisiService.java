package com.kolayvergi.service;

import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;

public interface AracBilgisiService {
    AracBilgisi createAracBilgisiForAlisveris(Alisveris alisveris, AracBilgisiCreateRequest request);
    AracBilgisiResponse updateAracBilgisi(AracBilgisiUpdateRequest request);
    AracBilgisiResponse getAracBilgisiById(Long id);
    AracBilgisiResponse getAracBilgisiByAlisverisId(Long alisverisId);
}
