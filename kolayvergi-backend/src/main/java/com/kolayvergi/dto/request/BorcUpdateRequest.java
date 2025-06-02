package com.kolayvergi.dto.request;

import com.kolayvergi.constant.ValidationConstants;
import jakarta.validation.constraints.NotNull;
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
public class BorcUpdateRequest {
    private UUID kullaniciId;

    @NotNull(message = ValidationConstants.TOPLAM_BORC_BOS_OLAMAZ)
    private BigDecimal toplamBorc;

    @NotNull(message = ValidationConstants.KALAN_BORC_BOS_OLAMAZ)
    private BigDecimal kalanBorc;
}
