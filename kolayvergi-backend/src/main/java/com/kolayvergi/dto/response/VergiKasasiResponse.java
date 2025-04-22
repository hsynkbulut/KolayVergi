package com.kolayvergi.dto.response;

import com.kolayvergi.entity.VergiTuru;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasiResponse {
    private Long id;
    private VergiTuru vergiTuru;
    private BigDecimal toplamBakiye;
}
