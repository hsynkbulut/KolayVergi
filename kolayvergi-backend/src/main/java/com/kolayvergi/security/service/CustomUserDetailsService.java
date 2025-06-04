package com.kolayvergi.security.service;

import com.kolayvergi.repository.KullaniciRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository;
    private final MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                    messageSource.getMessage("user.email_notfound", new Object[]{email}, LocaleContextHolder.getLocale())
                ));
    }
} 