package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.constant.swagger.TaksitOdemeSwaggerExample;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ODEMELER)
@Tag(name = "Taksit Ödeme İşlemleri", description = "Taksit ödemeleri için API endpoint'leri")
public interface TaksitOdemeController {

    @Operation(
            summary = SwaggerConstants.TAKSIT_ODE_SUMMARY,
            description = SwaggerConstants.TAKSIT_ODE_DESC
    )
    @ApiResponse(
        responseCode = "201",
        description = "Taksit ödemesi başarıyla gerçekleştirildi",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Taksit Ödeme Sonucu",
                description = "Taksit ödeme işlemi sonucu dönen örnek response",
                value = TaksitOdemeSwaggerExample.ODEME_SONUCU_RESPONSE
            )
        )
    )
    @ApiResponse(responseCode = "404", description = "Alışveriş bulunamadı")
    @PostMapping(ApiConstants.TAKSIT_ODEME)
    ResponseEntity<OdemeSonucu> taksitOdemeYap(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Taksit ödeme bilgileri",
            required = true,
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Taksit Ödeme",
                    description = "Taksit ödemesi yapmak için örnek request",
                    value = TaksitOdemeSwaggerExample.TAKSIT_ODEME_REQUEST
                )
            )
        )
        @RequestBody TaksitOdemeRequest request);

    @Operation(
            summary = SwaggerConstants.TAKSIT_ODEME_BILGISI_SUMMARY,
            description = SwaggerConstants.TAKSIT_ODEME_BILGISI_DESC
    )
    @ApiResponse(
        responseCode = "200",
        description = "Taksit ödeme detayları başarıyla getirildi",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                description = "Taksit ödeme detaylarını dönen örnek response",
                value = TaksitOdemeSwaggerExample.TAKSIT_ODEME_RESPONSE
            )
        )
    )
    @ApiResponse(responseCode = "404", description = "Belirtilen taksit numarasına ait ödeme bilgisi bulunamadı")
    @GetMapping(ApiConstants.TAKSIT_ODEME_DETAY)
    ResponseEntity<OdemeSonucu> taksitOdemeDetaylariniGetir(@PathVariable String taksitNo,
                                                   @RequestParam OdemeTuru odemeTuru);
}

