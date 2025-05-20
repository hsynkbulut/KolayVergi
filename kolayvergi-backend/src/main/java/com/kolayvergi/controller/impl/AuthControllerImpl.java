package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.AuthController;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.request.LoginRequest;
import com.kolayvergi.dto.response.JwtResponse;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    public ResponseEntity<JwtResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @Override
    public ResponseEntity<KullaniciResponse> register(@Valid @RequestBody KullaniciCreateRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<KullaniciResponse> updateProfile(@Valid @RequestBody KullaniciUpdateRequest request) {
        return ResponseEntity.ok(authService.updateProfile(request));
    }
} 