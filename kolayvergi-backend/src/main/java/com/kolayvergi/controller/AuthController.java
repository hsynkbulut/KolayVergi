package com.kolayvergi.controller;

import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.constant.swagger.AuthSwaggerExample;
import com.kolayvergi.constant.swagger.KullaniciSwaggerExample;
import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.request.LoginRequest;
import com.kolayvergi.dto.response.JwtResponse;
import com.kolayvergi.dto.response.KullaniciResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.AUTH)
@Tag(name = "Authentication İşlemleri", description = "Authentication işlemleri için API endpoint'leri")
public interface AuthController {

    @Operation(
            summary = SwaggerConstants.LOGIN_SUMMARY,
            description = SwaggerConstants.LOGIN_DESC
    )
    @ApiResponse(
            responseCode = "200",
            description = "Login başarılı",
            content = @Content(examples = @ExampleObject(description= "Giriş/Token yenileme sonucu dönen örnek response", value = AuthSwaggerExample.JWT_RESPONSE_ORNEK))
    )
    @ApiResponse(responseCode = "401", description = "Geçersiz kimlik bilgileri")
    @PostMapping(ApiConstants.AUTH_LOGIN)
    ResponseEntity<JwtResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Login için örnek request",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Login Request",
                        value = AuthSwaggerExample.LOGIN_REQUEST_ORNEK
                    )
                )
            )
            @Valid @RequestBody LoginRequest loginRequest);

    @Operation(
            summary = SwaggerConstants.REFRESH_TOKEN_SUMMARY,
            description = SwaggerConstants.REFRESH_TOKEN_DESC
    )
    @ApiResponse(
            responseCode = "200",
            description = "Token yenileme başarılı",
            content = @Content(examples = @ExampleObject(description = "Giriş/Token yenileme sonucu dönen örnek response", value = AuthSwaggerExample.JWT_RESPONSE_ORNEK))
    )
    @ApiResponse(responseCode = "401", description = "Geçersiz refresh token")
    @PostMapping(ApiConstants.AUTH_REFRESH)
    ResponseEntity<JwtResponse> refreshToken(@RequestHeader("Authorization") String refreshToken);

    @Operation(
            summary = SwaggerConstants.REGISTER_SUMMARY,
            description = SwaggerConstants.REGISTER_DESC
    )
    @ApiResponse(
            responseCode = "201",
            description = "Kullanıcı başarıyla oluşturuldu",
            content = @Content(examples = @ExampleObject(description = "Kullanıcı girişi için örnek response", value = KullaniciSwaggerExample.KULLANICI_RESPONSE))
    )
    @PostMapping(ApiConstants.AUTH_REGISTER)
    ResponseEntity<KullaniciResponse> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Kayıt için örnek request",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Register Request",
                        value = AuthSwaggerExample.REGISTER_REQUEST
                    )
                )
            )
            @Valid @RequestBody KullaniciCreateRequest request);

    @Operation(
            summary = SwaggerConstants.UPDATE_PROFILE_SUMMARY,
            description = SwaggerConstants.UPDATE_PROFILE_DESC
    )
    @ApiResponse(
            responseCode = "200",
            description = "Kullanıcı bilgileri başarıyla güncellendi",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Update Profile Response",
                    value = KullaniciSwaggerExample.KULLANICI_RESPONSE
                )
            )
    )
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @PutMapping(ApiConstants.UPDATE)
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<KullaniciResponse> updateProfile(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Profil güncelleme için örnek request",
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Update Profile Request",
                        value = AuthSwaggerExample.UPDATE_PROFILE_REQUEST
                    )
                )
            )
            @Valid @RequestBody KullaniciUpdateRequest request);
}