package com.kolayvergi.service;

import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.request.LoginRequest;
import com.kolayvergi.dto.response.JwtResponse;
import com.kolayvergi.dto.response.KullaniciResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
    JwtResponse refreshToken(String refreshToken);
    KullaniciResponse register(KullaniciCreateRequest request);
    KullaniciResponse updateProfile(KullaniciUpdateRequest request);
} 