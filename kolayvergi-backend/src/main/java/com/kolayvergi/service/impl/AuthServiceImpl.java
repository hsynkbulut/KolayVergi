package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.KullaniciMapper;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.request.LoginRequest;
import com.kolayvergi.dto.response.JwtResponse;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Role;
import com.kolayvergi.generator.VknGenerator;
import com.kolayvergi.repository.KullaniciRepository;
import com.kolayvergi.security.jwt.JwtService;
import com.kolayvergi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtTokenProvider;
    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final KullaniciMapper kullaniciMapper;
    private final UserDetailsService userDetailsService;
    private final VknGenerator vknGenerator;
    private final MessageSource messageSource;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(jwt, refreshToken, userDetails.getUsername(), roles);
    }

    @Override
    public JwtResponse refreshToken(String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken)) {
            String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            
            String newAccessToken = jwtTokenProvider.generateToken(authentication);
            String newRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return new JwtResponse(newAccessToken, newRefreshToken, username, roles);
        }
        throw new IllegalArgumentException(messageSource.getMessage("auth.jwt_invalid", null, LocaleContextHolder.getLocale()));
    }

    @Override
    @Transactional
    public KullaniciResponse register(KullaniciCreateRequest request) {
        if (kullaniciRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException(messageSource.getMessage("auth.email_in_use", null, LocaleContextHolder.getLocale()));
        }

        Kullanici kullanici = kullaniciMapper.kullaniciCreateRequestToKullanici(request);
        kullanici.setSifre(passwordEncoder.encode(request.getSifre()));
        kullanici.setRol(Role.ROLE_USER);
        kullanici.setVkn(vknGenerator.generateUniqueVkn());

        return kullaniciMapper.kullaniciToKullaniciResponse(kullaniciRepository.save(kullanici));
    }

    @Override
    @Transactional
    public KullaniciResponse updateProfile(KullaniciUpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Kullanici kullanici = kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new java.util.NoSuchElementException(messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale())));

        kullaniciMapper.updateKullaniciFromRequest(request, kullanici);
        return kullaniciMapper.kullaniciToKullaniciResponse(kullaniciRepository.save(kullanici));
    }

} 