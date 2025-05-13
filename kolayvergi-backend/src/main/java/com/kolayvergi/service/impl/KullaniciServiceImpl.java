package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Role;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Kullanici currentUser = getCurrentUser();
        validateDeletePermission(currentUser, id);
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

    public boolean isCurrentUser(Long userId) {
        try {
            Kullanici currentUser = getCurrentUser();
            return currentUser.getId().equals(userId);
        } catch (Exception e) {
            return false;
        }
    }

    private Kullanici getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return kullaniciRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Oturum açmış kullanıcı bulunamadı"));
    }

    private void validateDeletePermission(Kullanici currentUser, Long targetUserId) {
        if (currentUser.getRol() == Role.ROLE_ADMIN || currentUser.getId().equals(targetUserId)) {
            return;
        }
        throw new RuntimeException("Bu kullanıcıyı silme yetkiniz bulunmamaktadır. Sadece kendi hesabınızı veya admin olarak tüm hesapları silebilirsiniz.");
    }
}
