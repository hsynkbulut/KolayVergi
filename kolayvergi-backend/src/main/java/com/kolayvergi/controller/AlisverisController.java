package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.swagger.AlisverisSwaggerExample;
import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ALISVERISLER)
@Tag(name = "Alışveriş İşlemleri", description = "Alışveriş için API endpoint'leri")
public interface AlisverisController {

    @Operation(
            summary = SwaggerConstants.CREATE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.CREATE_ALISVERIS_DESC
    )
    @ApiResponse(
            responseCode = "201",
            description = "Alışveriş başarıyla oluşturuldu",
            content = @Content(examples = {
                @ExampleObject(name = "Otomobil Alışverişi Response", value = AlisverisSwaggerExample.OTOMOBIL_ALISVERIS_RESPONSE),
                @ExampleObject(name = "Diğer Ürün Alışverişi Response", value = AlisverisSwaggerExample.DIGER_URUN_ALISVERIS_RESPONSE)
            })
    )
    @PostMapping
    ResponseEntity<AlisverisResponse> createAlisveris(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Alışveriş oluşturma request",
                    content = @Content(examples = {
                        @ExampleObject(name = "Otomobil Alışverişi Request", value = AlisverisSwaggerExample.OTOMOBIL_ALISVERIS_REQUEST),
                        @ExampleObject(name = "Diğer Ürün Alışverişi Request", value = AlisverisSwaggerExample.DIGER_URUN_ALISVERIS_REQUEST)
                    })
            )
            @RequestBody @Valid AlisverisCreateRequest request);

    @Operation(
            summary = SwaggerConstants.GET_ALISVERIS_SUMMARY,
            description = SwaggerConstants.GET_ALISVERIS_DESC
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alışveriş başarıyla getirildi",
            content = @Content(examples = {
                @ExampleObject(name = "Otomobil Alışverişi Response", value = AlisverisSwaggerExample.OTOMOBIL_ALISVERIS_RESPONSE),
                @ExampleObject(name = "Diğer Ürün Alışverişi Response", value = AlisverisSwaggerExample.DIGER_URUN_ALISVERIS_RESPONSE)
            })
    )
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @GetMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> getAlisverisById(@PathVariable("id") Long id);

    @Operation(
            summary = SwaggerConstants.UPDATE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.UPDATE_ALISVERIS_DESC
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alışveriş başarıyla güncellendi",
            content = @Content(examples = {
                @ExampleObject(name = "Otomobil Alışverişi Response", value = AlisverisSwaggerExample.OTOMOBIL_ALISVERIS_RESPONSE),
                @ExampleObject(name = "Diğer Ürün Alışverişi Response", value = AlisverisSwaggerExample.DIGER_URUN_ALISVERIS_RESPONSE)
            })
    )
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @PutMapping(ApiConstants.ID)
    ResponseEntity<AlisverisResponse> updateAlisveris(
            @PathVariable("id") Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Alışveriş güncelleme isteği",
                    content = @Content(examples = {
                        @ExampleObject(name = "Otomobil Alışverişi Request", value = AlisverisSwaggerExample.OTOMOBIL_ALISVERIS_REQUEST),
                        @ExampleObject(name = "Diğer Ürün Alışverişi Request", value = AlisverisSwaggerExample.DIGER_URUN_ALISVERIS_REQUEST)
                    })
            )
            @RequestBody @Valid AlisverisCreateRequest request);

    @Operation(
            summary = SwaggerConstants.DELETE_ALISVERIS_SUMMARY,
            description = SwaggerConstants.DELETE_ALISVERIS_DESC
    )
    @ApiResponse(responseCode = "204", description = "Alışveriş başarıyla silindi")
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @DeleteMapping(ApiConstants.ID)
    ResponseEntity<Void> deleteAlisveris(@PathVariable("id") Long id);
}
