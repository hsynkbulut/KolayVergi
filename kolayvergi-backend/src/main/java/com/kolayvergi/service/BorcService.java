package com.kolayvergi.service;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;

import java.util.Optional;
import java.util.UUID;

public interface BorcService {

    BorcResponse createBorc(BorcCreateRequest borc);
    BorcResponse getBorc();
    BorcResponse getBorcByKullaniciId(UUID kullaniciId);
    Optional<BorcResponse> getBorcByKullaniciIdSafely(UUID kullaniciId);
    BorcResponse updateBorc(UUID id, BorcUpdateRequest updateBorc);
    void deleteBorcByKullaniciId(UUID kullaniciId);
}
