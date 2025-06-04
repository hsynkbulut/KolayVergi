package com.kolayvergi.dto.request;

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

    @NotNull(message = "validation.toplam_borc_bos_olamaz")
    private BigDecimal toplamBorc;

    @NotNull(message = "validation.kalan_borc_bos_olamaz")
    private BigDecimal kalanBorc;
}
