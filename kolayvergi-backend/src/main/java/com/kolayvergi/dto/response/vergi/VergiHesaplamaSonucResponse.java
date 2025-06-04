package com.kolayvergi.dto.response.vergi;

import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class VergiHesaplamaSonucResponse {
    private final BigDecimal toplamVergiTutari;
    private final OtvVergisi otvVergisi;
    private final KdvVergisi kdvVergisi;
    private final MtvVergisi mtvVergisi;
} 