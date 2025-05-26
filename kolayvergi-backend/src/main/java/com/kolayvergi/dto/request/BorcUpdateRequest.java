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
//    @NotNull(message = "UserId boş bırakılamaz")
    private UUID kullaniciId;

    @NotNull(message = "Toplam borç alanı boş bırakılamaz")
    private BigDecimal toplamBorc;

    @NotNull(message = "Kalan borç alanı boş bırakılamaz")
    private BigDecimal kalanBorc;
}
