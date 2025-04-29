package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.entity.enums.OdemeTuru;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@RequestMapping(ApiConstants.ODEMELER)
public interface OdemeController {

    @PostMapping
    ResponseEntity<Boolean> taksitOde(@RequestBody TaksitOdemeRequest request);


}
