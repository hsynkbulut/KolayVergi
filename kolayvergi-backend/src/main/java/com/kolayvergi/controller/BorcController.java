package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.constant.SwaggerConstants;
import com.kolayvergi.dto.response.BorcResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = ApiConstants.BORCLAR)
public interface BorcController {

    @Operation(
            summary = SwaggerConstants.GET_BORC_SUMMARY,
            description = SwaggerConstants.GET_BORC_DESC
    )
    @ApiResponse(responseCode = "200", description = "Borç bilgisi başarıyla getirildi")
    @ApiResponse(responseCode = "404", description = "Borç bulunamadı")
    @GetMapping(path = ApiConstants.ID)
    ResponseEntity<BorcResponse> getBorcById(@PathVariable(name = "id") Long id);
}
