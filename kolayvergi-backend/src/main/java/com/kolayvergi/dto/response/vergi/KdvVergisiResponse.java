package com.kolayvergi.dto.response.vergi;

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
public class KdvVergisiResponse {
    private UUID alisverisId;
    private UrunTuru urunTuru;
    private BigDecimal fiyat;
}
