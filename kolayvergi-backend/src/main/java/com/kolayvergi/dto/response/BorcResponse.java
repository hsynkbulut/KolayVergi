package com.kolayvergi.dto.response;

import lombok.*;

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
