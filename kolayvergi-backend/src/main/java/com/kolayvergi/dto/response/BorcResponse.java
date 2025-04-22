package com.kolayvergi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorcResponse {
    private Long id;
    private BigDecimal toplamBorc;
    private BigDecimal kalanBorc;
}
