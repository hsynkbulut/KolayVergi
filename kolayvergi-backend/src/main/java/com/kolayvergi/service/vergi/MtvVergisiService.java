package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.request.mtv.MtvVergisiCreateRequest;
import com.kolayvergi.dto.request.mtv.MtvVergisiUpdateRequest;
import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;

import java.util.List;

public interface MtvVergisiService {
    MtvVergisiResponse createMtvVergisi(MtvVergisiCreateRequest request);
    MtvVergisiResponse updateMtvVergisi(Long id, MtvVergisiUpdateRequest updateRequest);
    MtvVergisiResponse getMtvVergisiById(Long id);
    List<MtvVergisiResponse> getMtvVergisiAll();
}
