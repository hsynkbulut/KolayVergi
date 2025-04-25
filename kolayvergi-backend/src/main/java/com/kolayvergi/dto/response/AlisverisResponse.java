package com.kolayvergi.dto.response;

import com.kolayvergi.entity.enums.UrunTuru;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlisverisResponse {
    private UrunTuru urunTuru;
    private BigDecimal tutar;
}
