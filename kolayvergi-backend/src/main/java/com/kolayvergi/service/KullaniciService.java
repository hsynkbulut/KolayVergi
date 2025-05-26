package com.kolayvergi.service;

import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;

import java.util.List;
import java.util.UUID;

public interface KullaniciService {
    KullaniciResponse getKullaniciById(UUID id);
    void deleteKullanici();
    Kullanici getKullanici(UUID id);
    List<KullaniciResponse> getAllKullanicilar();
    Kullanici getCurrentUser();
}
