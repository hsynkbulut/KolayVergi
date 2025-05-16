package com.kolayvergi.controller;

import com.kolayvergi.constant.swagger.KullaniciSwaggerExample;
import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.response.KullaniciResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiConstants.KULLANICILAR)
@Tag(name = "Kullanıcı İşlemleri", description = "Kullanıcı yönetimi için API endpoint'leri")
public interface KullaniciController {

    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    @GetMapping(ApiConstants.ID)
    @PreAuthorize("hasRole('ADMIN') or @kullaniciServiceImpl.isCurrentUser(#id)")
    @Operation(summary = SwaggerConstants.GET_KULLANICI_SUMMARY, description = SwaggerConstants.GET_KULLANICI_DESC)
    @ApiResponse(
            responseCode = "200",
            description = "Kullanıcı başarıyla getirildi",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Kullanıcı Detayları",
                            value = KullaniciSwaggerExample.KULLANICI_RESPONSE
                    )
            )
    )
    ResponseEntity<KullaniciResponse> getKullaniciById(@PathVariable Long id);

    @DeleteMapping(ApiConstants.DELETE)
    @PreAuthorize("hasRole('ADMIN') or @kullaniciServiceImpl.isCurrentUser(#id)")
    @Operation(summary = SwaggerConstants.DELETE_KULLANICI_SUMMARY, description = SwaggerConstants.DELETE_KULLANICI_DESC)
    @ApiResponse(responseCode = "204", description = "Kullanıcı başarıyla silindi")
    @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı")
    ResponseEntity<Void> deleteKullanici(@PathVariable Long id);

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = SwaggerConstants.GET_ALL_KULLANICILAR_SUMMARY, description = SwaggerConstants.GET_ALL_KULLANICILAR_DESC)
    @ApiResponse(
            responseCode = "200",
            description = "Tüm kullanıcılar başarıyla listelendi",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Kullanıcı Listesi",
                            value = KullaniciSwaggerExample.KULLANICI_LISTESI_RESPONSE
                    )
            )
    )
    ResponseEntity<List<KullaniciResponse>> getAllKullanicilar();
}
