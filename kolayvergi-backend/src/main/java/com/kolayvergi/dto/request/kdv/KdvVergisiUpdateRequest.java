package com.kolayvergi.dto.request.kdv;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KdvVergisiUpdateRequest {

    @DecimalMin(value = "0.01", message = "Fiyat 0'dan büyük olmalı.")
    private BigDecimal fiyat;

    @Size(min = 1, message = "Ürün kategorisi boş olamaz.")
    private String urunKategorisi;

    private UrunTuru urunTuru;
}
