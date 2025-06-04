package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.service.KullaniciService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    private final MessageSource messageSource;

    @Override
    public KullaniciResponse getKullaniciById(UUID id) {
        return kullaniciMapper.kullaniciToKullaniciResponse(getKullanici(id));
    }

    @Override
    @Transactional
    public void deleteKullanici() {
        kullaniciRepository.delete(getCurrentUser());
    }

    @Override
    public Kullanici getKullanici(UUID id) {
        return kullaniciRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.id_notfound", new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<KullaniciResponse> getAllKullanicilar() {
        return kullaniciRepository.findAll().stream()
                .map(kullaniciMapper::kullaniciToKullaniciResponse)
                .collect(Collectors.toList());
    }

    public boolean isCurrentUser(UUID userId) {
        try {
            return getCurrentUser().getId().equals(userId);
        } catch (Exception e) {
            return false;
        }
    }

    public Kullanici getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return kullaniciRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.session_notfound", null, LocaleContextHolder.getLocale())));
    }
}
