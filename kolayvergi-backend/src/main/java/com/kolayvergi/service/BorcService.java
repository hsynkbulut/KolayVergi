package com.kolayvergi.service;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;

public interface BorcService {

    BorcResponse createBorc(BorcCreateRequest borc);
    BorcResponse getBorc(Long id);
    BorcResponse updateBorc(Long id, BorcUpdateRequest updateBorc);
}
