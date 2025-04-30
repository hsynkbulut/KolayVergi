package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.TaksitOdemeRequest;
import com.kolayvergi.dto.response.OdemeSonucu;
import com.kolayvergi.entity.enums.OdemeTuru;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping(ApiConstants.ODEMELER)
public interface OdemeController {

//    @PostMapping
//    ResponseEntity<Boolean> taksitOde(@RequestBody TaksitOdemeRequest request);

    @PostMapping
    ResponseEntity<OdemeSonucu> taksitOde(@RequestBody TaksitOdemeRequest request);

    @GetMapping("/{taksitNo}")
    ResponseEntity<OdemeSonucu> taksitOdemeBilgisi(@PathVariable String taksitNo,
                                                   @RequestParam OdemeTuru odemeTuru);


}
