package com.kolayvergi.dto.request.kdv;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KdvVergisiCreateRequest {

    @NotNull(message = "Alışveriş ID'si boş olamaz.")
    private Long alisverisId;

    @NotNull(message = "Fiyat boş olamaz.")
    @DecimalMin(value = "0.01", message = "Fiyat 0'dan büyük olmalı.")
    private BigDecimal fiyat;

    @NotBlank(message = "Ürün kategorisi boş olamaz.")
    private String urunKategorisi;

    @NotBlank(message = "Ürün türü boş olamaz.")
    private UrunTuru urunTuru;
}
