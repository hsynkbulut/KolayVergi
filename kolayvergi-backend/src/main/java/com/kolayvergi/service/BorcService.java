package com.kolayvergi.service;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;

import java.util.Optional;

public interface BorcService {

    BorcResponse createBorc(BorcCreateRequest borc);
    BorcResponse getBorc(Long id);
    BorcResponse getBorcByKullaniciId(Long kullaniciId);
    Optional<BorcResponse> getBorcByKullaniciIdSafely(Long kullaniciId);
    BorcResponse updateBorc(Long id, BorcUpdateRequest updateBorc);
}
