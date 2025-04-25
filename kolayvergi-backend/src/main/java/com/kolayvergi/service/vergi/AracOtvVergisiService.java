package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.request.otv.AracOtvVergisiCreateRequest;
import com.kolayvergi.dto.request.otv.AracOtvVergisiUpdateRequest;
import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;

import java.util.List;

public interface AracOtvVergisiService {
    AracOtvVergisiResponse createAracOtvVergisi(AracOtvVergisiCreateRequest request);
    AracOtvVergisiResponse updateAracOtvVergisi(Long id, AracOtvVergisiUpdateRequest request);
    AracOtvVergisiResponse getAracOtvVergisiById(Long id);
    List<AracOtvVergisiResponse> getAracOtvVergisiAll();
}
