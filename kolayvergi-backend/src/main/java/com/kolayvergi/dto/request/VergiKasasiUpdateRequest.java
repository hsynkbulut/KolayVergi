package com.kolayvergi.dto.request;

import com.kolayvergi.entity.VergiTuru;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasiUpdateRequest {
    private VergiTuru vergiTuru;
    private BigDecimal toplamBakiye;
}
