package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ALISVERISLER)
public interface AlisverisController {

    @Operation(
            summary = SwaggerConstants.CREATE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.CREATE_ALISVERIS_DESC
    )
    @ApiResponse(responseCode = "201", description = "Alışveriş başarıyla oluşturuldu")
    @PostMapping
    ResponseEntity<AlisverisResponse> createAlisveris(@RequestBody @Valid AlisverisCreateRequest request);

    @Operation(
            summary = SwaggerConstants.GET_ALISVERIS_SUMMARY,
            description = SwaggerConstants.GET_ALISVERIS_DESC
    )
    @ApiResponse(responseCode = "200", description = "Alışveriş başarıyla getirildi")
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @GetMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> getAlisverisById(@PathVariable("id") Long id);

    @Operation(
            summary = SwaggerConstants.UPDATE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.UPDATE_ALISVERIS_DESC
    )
    @ApiResponse(responseCode = "200", description = "Alışveriş başarıyla güncellendi")
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @PutMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> updateAlisveris(@PathVariable("id") Long id,
                                                      @RequestBody @Valid AlisverisUpdateRequest request);

    @Operation(
            summary = SwaggerConstants.DELETE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.DELETE_ALISVERIS_DESC
    )
    @ApiResponse(responseCode = "204", description = "Alışveriş başarıyla silindi")
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @DeleteMapping(ApiConstants.ID)
    ResponseEntity<Void> deleteAlisveris(@PathVariable("id") Long id);
}
