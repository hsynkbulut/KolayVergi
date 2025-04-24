package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;

    @Override
    public KullaniciResponse createKullanici(KullaniciCreateRequest request) {
        Kullanici kullanici = kullaniciMapper.kullaniciCreateRequestToKullanici(request);
        kullanici.setAlisverisler(new ArrayList<>());
        Kullanici dbKullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.kullaniciToKullaniciResponse(dbKullanici);
    }

    @Override
    public KullaniciResponse getKullaniciById(Long id) {
        Kullanici kullanici = getKullanici(id);
        return kullaniciMapper.kullaniciToKullaniciResponse(kullanici);
    }

    @Override
    public KullaniciResponse updateKullanici(Long id, KullaniciUpdateRequest updateKullaniciRequest) {
        Kullanici kullanici = getKullanici(id);
        kullaniciMapper.updateKullaniciFromRequest(updateKullaniciRequest, kullanici);

        Kullanici dbKullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.kullaniciToKullaniciResponse(dbKullanici);
    }

    @Override
    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }

    @Override
    public Kullanici getKullanici(Long id){
        return kullaniciRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));
    }
}
