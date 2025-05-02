package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.ODEMELER)
public interface OdemeController {

    @Operation(
            summary = SwaggerConstants.TAKSIT_ODE_SUMMARY,
            description = SwaggerConstants.TAKSIT_ODE_DESC
    )
    @ApiResponse(responseCode = "201", description = "Taksit başarıyla ödendi")
    @ApiResponse(responseCode = "400", description = "Geçersiz ödeme verisi")
    @PostMapping
    ResponseEntity<OdemeSonucu> taksitOde(@RequestBody TaksitOdemeRequest request);

    @Operation(
            summary = SwaggerConstants.TAKSIT_ODEME_BILGISI_SUMMARY,
            description = SwaggerConstants.TAKSIT_ODEME_BILGISI_DESC
    )
    @ApiResponse(responseCode = "200", description = "Taksit ödeme bilgisi başarıyla getirildi")
    @ApiResponse(responseCode = "404", description = "Belirtilen taksit numarasına ait ödeme bilgisi bulunamadı")
    @GetMapping("/{taksitNo}")
    ResponseEntity<OdemeSonucu> taksitOdemeBilgisi(@PathVariable String taksitNo,
                                                   @RequestParam OdemeTuru odemeTuru);
}

