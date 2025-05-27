package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.dto.response.TaksitResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiConstants.TAKSITLER)
@Tag(name = "Taksit İşlemleri", description = "Taksit yönetimi için API endpoint'leri")
public interface TaksitController {

    @Operation(
            summary = "Kullanıcının Taksitlerini Getir",
            description = "Giriş yapmış kullanıcının taksitlerini listeler"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Taksitler başarıyla listelendi",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<List<TaksitResponse>> getCurrentUserTaksitler();
} 