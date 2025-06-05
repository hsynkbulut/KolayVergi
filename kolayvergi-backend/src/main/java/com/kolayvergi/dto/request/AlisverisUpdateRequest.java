package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlisverisUpdateRequest {

    @NotNull(message = "validation.urun_turu_bos_olamaz")
    private UrunTuru urunTuru;

    @Valid
    private AracBilgisiUpdateRequest aracBilgisi;

    @NotNull(message = "validation.tutar_bos_olamaz")
    private BigDecimal tutar;

    @NotNull(message = "validation.taksit_sayisi_bos_olamaz")
    private Integer taksitSayisi;
}
