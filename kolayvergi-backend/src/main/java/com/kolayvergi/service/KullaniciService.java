package com.kolayvergi.service;

import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;

public interface KullaniciService {

    KullaniciResponse createKullanici(KullaniciCreateRequest request);
    Kullanici getKullanici(Long id);
    KullaniciResponse getKullaniciById(Long id);
    KullaniciResponse updateKullanici(Long id, KullaniciUpdateRequest updateKullaniciRequest);
    void deleteKullanici(Long id);
}
