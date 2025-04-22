package com.kolayvergi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorcCreateRequest {
    @NotNull(message = "Kullanıcı ID alanı boş bırakılamaz")
    private Long kullaniciId;

    @NotNull(message = "Toplam borç alanı boş bırakılamaz")
    private BigDecimal toplamBorc;

    @NotNull(message = "Kalan borç alanı boş bırakılamaz")
    private BigDecimal kalanBorc;
}

