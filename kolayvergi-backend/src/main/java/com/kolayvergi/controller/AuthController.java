package com.kolayvergi.controller;

import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.request.LoginRequest;
import com.kolayvergi.dto.response.JwtResponse;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = SwaggerConstants.LOGIN_SUMMARY,
            description = SwaggerConstants.LOGIN_DESC
    )
    @ApiResponse(responseCode = "200", description = "Giriş başarılı")
    @ApiResponse(responseCode = "401", description = "Geçersiz kimlik bilgileri")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Operation(
            summary = SwaggerConstants.REFRESH_TOKEN_SUMMARY,
            description = SwaggerConstants.REFRESH_TOKEN_DESC
    )
    @ApiResponse(responseCode = "200", description = "Token yenileme başarılı")
    @ApiResponse(responseCode = "401", description = "Geçersiz refresh token")
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @Operation(
            summary = SwaggerConstants.REGISTER_SUMMARY,
            description = SwaggerConstants.REGISTER_DESC
    )
    @ApiResponse(responseCode = "201", description = "Kullanıcı başarıyla oluşturuldu")
    @PostMapping("/register")
    public ResponseEntity<KullaniciResponse> register(@Valid @RequestBody KullaniciCreateRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(
            summary = SwaggerConstants.UPDATE_PROFILE_SUMMARY,
            description = SwaggerConstants.UPDATE_PROFILE_DESC
    )
    @ApiResponse(responseCode = "200", description = "Kullanıcı bilgileri başarıyla güncellendi")
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<KullaniciResponse> updateProfile(@Valid @RequestBody KullaniciUpdateRequest request) {
        return ResponseEntity.ok(authService.updateProfile(request));
    }
} 