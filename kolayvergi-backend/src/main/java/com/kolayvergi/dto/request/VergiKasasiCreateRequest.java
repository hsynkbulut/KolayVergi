package com.kolayvergi.dto.request;

import com.kolayvergi.entity.VergiTuru;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasiCreateRequest {
    private VergiTuru vergiTuru;
    private BigDecimal toplamBakiye;
}

