package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.request.kdv.KdvVergisiCreateRequest;
import com.kolayvergi.dto.request.kdv.KdvVergisiUpdateRequest;
import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;

import java.util.List;

public interface KdvVergisiService {
    KdvVergisiResponse createKdvVergisi(KdvVergisiCreateRequest request);
    KdvVergisiResponse updateKdvVergisi(Long id, KdvVergisiUpdateRequest updateRequest);
    KdvVergisiResponse getKdvVergisiById(Long id);
    List<KdvVergisiResponse> getKdvVergisiAll();
}
