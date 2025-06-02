package com.kolayvergi.dto.response;

import com.kolayvergi.entity.enums.UrunTuru;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlisverisResponse {
    private UUID id;
    private UrunTuru urunTuru;
    private BigDecimal tutar;
    private Integer taksitSayisi;
    private AracBilgisiResponse aracBilgisi;
}