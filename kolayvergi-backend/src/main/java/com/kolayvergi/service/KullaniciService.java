package com.kolayvergi.service;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import java.util.List;

public interface KullaniciService {

    KullaniciResponse createKullanici(KullaniciCreateRequest request);
    KullaniciResponse getKullaniciById(Long id);
    KullaniciResponse updateKullanici(Long id, KullaniciUpdateRequest updateKullaniciRequest);
    void deleteKullanici(Long id);
}
