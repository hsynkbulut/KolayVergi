package com.kolayvergi.dto.response;

import com.kolayvergi.entity.enums.VergiTuru;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasiResponse {
    private Long id;
    private VergiTuru vergiTuru;
    private BigDecimal toplamBakiye;
}
