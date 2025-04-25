package com.kolayvergi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorcUpdateRequest {
    @NotNull(message = "UserId boş bırakılamaz")
    private Long kullaniciId;

    @NotNull(message = "Toplam borç alanı boş bırakılamaz")
    private BigDecimal toplamBorc;

    @NotNull(message = "Kalan borç alanı boş bırakılamaz")
    private BigDecimal kalanBorc;
}
