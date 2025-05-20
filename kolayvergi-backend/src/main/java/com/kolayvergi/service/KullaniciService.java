package com.kolayvergi.service;

import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;

import java.util.List;

public interface KullaniciService {
    KullaniciResponse getKullaniciById(Long id);
    void deleteKullanici(Long id);
    Kullanici getKullanici(Long id);
    List<KullaniciResponse> getAllKullanicilar();
    Kullanici getCurrentUser();
}
