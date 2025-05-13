package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;

    @Override
    public KullaniciResponse getKullaniciById(Long id) {
        Kullanici kullanici = getKullanici(id);
        return kullaniciMapper.kullaniciToKullaniciResponse(kullanici);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }

    @Override
    public Kullanici getKullanici(Long id) {
        return kullaniciRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));
    }

    @Override
    public List<KullaniciResponse> getAllKullanicilar() {
        return kullaniciRepository.findAll().stream()
                .map(kullaniciMapper::kullaniciToKullaniciResponse)
                .collect(Collectors.toList());
    }
}
