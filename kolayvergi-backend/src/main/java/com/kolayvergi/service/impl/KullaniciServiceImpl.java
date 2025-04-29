package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.generator.VknGenerator;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;
    private final VknGenerator vknGenerator;


    @Transactional(readOnly = false)
    @Override
    public KullaniciResponse createKullanici(KullaniciCreateRequest request) {
        Kullanici kullanici = kullaniciMapper.kullaniciCreateRequestToKullanici(request);
        kullanici.setAlisverisler(new ArrayList<>());
        kullanici.setVkn(vknGenerator.generate());
        Kullanici dbKullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.kullaniciToKullaniciResponse(dbKullanici);
    }

    @Override
    public KullaniciResponse getKullaniciById(Long id) {
        Kullanici kullanici = getKullanici(id);
        return kullaniciMapper.kullaniciToKullaniciResponse(kullanici);
    }

    @Transactional(readOnly = false)
    @Override
    public KullaniciResponse updateKullanici(Long id, KullaniciUpdateRequest updateKullaniciRequest) {
        Kullanici kullanici = getKullanici(id);
        kullaniciMapper.updateKullaniciFromRequest(updateKullaniciRequest, kullanici);

        Kullanici dbKullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.kullaniciToKullaniciResponse(dbKullanici);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }

    @Override
    public Kullanici getKullanici(Long id){
        return kullaniciRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));
    }

    private String generateUniqueVkn() {
        String vkn = vknGenerator.generate();
        int denemeSayisi = 1;

        while (kullaniciRepository.existsByVkn(vkn)) {
            if (denemeSayisi >= 50) {
                throw new RuntimeException("Benzersiz VKN üretilemedi.");
            }

            vkn = vknGenerator.generate();
            denemeSayisi++;
        }
        return vkn;
    }


}
