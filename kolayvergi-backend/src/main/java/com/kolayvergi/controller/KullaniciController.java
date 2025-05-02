package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.KULLANICILAR)
public interface KullaniciController {

    @Operation(
            summary = SwaggerConstants.CREATE_KULLANICI_SUMMARY,
            description = SwaggerConstants.CREATE_KULLANICI_DESC
    )
    @ApiResponse(responseCode = "201", description = "Kullanıcı başarıyla oluşturuldu")
    @PostMapping
    ResponseEntity<KullaniciResponse> createKullanici(@RequestBody @Valid KullaniciCreateRequest request);

    @Operation(
            summary = SwaggerConstants.GET_KULLANICI_SUMMARY,
            description = SwaggerConstants.GET_KULLANICI_DESC
    )
    @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla getirildi")
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @GetMapping("/{id}")
    ResponseEntity<KullaniciResponse> getKullaniciById(@PathVariable(name = "id") Long id);

    @Operation(
            summary = SwaggerConstants.UPDATE_KULLANICI_SUMMARY,
            description = SwaggerConstants.UPDATE_KULLANICI_DESC
    )
    @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla güncellendi")
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @PutMapping("/{id}")
    ResponseEntity<KullaniciResponse> updateKullanici(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid KullaniciUpdateRequest updateKullaniciRequest
    );

    @Operation(
            summary = SwaggerConstants.DELETE_KULLANICI_SUMMARY,
            description = SwaggerConstants.DELETE_KULLANICI_DESC
    )
    @ApiResponse(responseCode = "204", description = "Kullanıcı başarıyla silindi")
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteKullanici(@PathVariable(name = "id") Long id);
}
