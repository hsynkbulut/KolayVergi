package com.kolayvergi.security.service;

import com.kolayvergi.constant.KullaniciConstants;
import com.kolayvergi.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(KullaniciConstants.KULLANICI_EMAIL_BULUNAMADI, email)));
    }
} 