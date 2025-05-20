package com.kolayvergi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorcResponse {
    private Long id;
    private BigDecimal toplamBorc;
    private BigDecimal kalanBorc;
}
