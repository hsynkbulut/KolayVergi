package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;

    @Override
    public KullaniciResponse getKullaniciById(UUID id) {
        Kullanici kullanici = getKullanici(id);
        return kullaniciMapper.kullaniciToKullaniciResponse(kullanici);
    }

    @Override
    @Transactional
    public void deleteKullanici() {
        Kullanici currentUser = getCurrentUser();
        kullaniciRepository.delete(currentUser);
    }

    @Override
    public Kullanici getKullanici(UUID id) {
        return kullaniciRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));
    }

    @Override
    public List<KullaniciResponse> getAllKullanicilar() {
        return kullaniciRepository.findAll().stream()
                .map(kullaniciMapper::kullaniciToKullaniciResponse)
                .collect(Collectors.toList());
    }

    public boolean isCurrentUser(UUID userId) {
        try {
            Kullanici currentUser = getCurrentUser();
            return currentUser.getId().equals(userId);
        } catch (Exception e) {
            return false;
        }
    }

    public Kullanici getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return kullaniciRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Oturum açmış kullanıcı bulunamadı"));
    }
}
