package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.swagger.BorcSwaggerExample;
import com.kolayvergi.constant.swagger.SwaggerConstants;
import com.kolayvergi.dto.response.BorcResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = ApiConstants.BORCLAR)
@Tag(name = "Borç İşlemleri", description = "Borç yönetimi için API endpoint'leri")
public interface BorcController {

    @Operation(
            summary = SwaggerConstants.GET_BORC_SUMMARY,
            description = SwaggerConstants.GET_BORC_DESC
    )
    @ApiResponse(
        responseCode = "200",
        description = "Borç bilgisi başarıyla getirildi",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                name = "Borç Detayları",
                description = "Borç bilgilerini içeren örnek response",
                value = BorcSwaggerExample.BORC_RESPONSE
            )
        )
    )
    @ApiResponse(responseCode = "404", description = "Borç bulunamadı")
    @GetMapping(path = ApiConstants.ID)
    ResponseEntity<BorcResponse> getBorcById(@PathVariable(name = "id") Long id);
}
