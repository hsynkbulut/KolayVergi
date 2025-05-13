package com.kolayvergi.controller;

import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.response.KullaniciResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kullanicilar")
@Tag(name = "Kullanıcı Yönetimi", description = "Kullanıcı yönetimi için API endpoint'leri")
public interface KullaniciController {

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @kullaniciServiceImpl.isCurrentUser(#id)")
    @Operation(summary = SwaggerConstants.GET_KULLANICI_SUMMARY, description = SwaggerConstants.GET_KULLANICI_DESC)
    @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla getirildi")
    ResponseEntity<KullaniciResponse> getKullaniciById(@PathVariable Long id);

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN') or @kullaniciServiceImpl.isCurrentUser(#id)")
    @Operation(summary = SwaggerConstants.DELETE_KULLANICI_SUMMARY, description = SwaggerConstants.DELETE_KULLANICI_DESC)
    @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla silindi")
    ResponseEntity<Void> deleteKullanici(@PathVariable Long id);

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = SwaggerConstants.GET_ALL_KULLANICILAR_SUMMARY, description = SwaggerConstants.GET_ALL_KULLANICILAR_DESC)
    @ApiResponse(responseCode = "200", description = "Tüm kullanıcılar başarıyla listelendi")
    ResponseEntity<List<KullaniciResponse>> getAllKullanicilar();
}
