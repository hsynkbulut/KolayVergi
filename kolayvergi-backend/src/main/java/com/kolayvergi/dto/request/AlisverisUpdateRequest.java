package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.constant.ValidationConstants;
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

    @NotNull(message = ValidationConstants.URUN_TURU_BOS_OLAMAZ)
    private UrunTuru urunTuru;

    @Valid
    private AracBilgisiCreateRequest aracBilgisi;

    @NotNull(message = ValidationConstants.TUTAR_BOS_OLAMAZ)
    private BigDecimal tutar;

    @NotNull(message = ValidationConstants.TAKSIT_SAYISI_BOS_OLAMAZ)
    private Integer taksitSayisi;
}
