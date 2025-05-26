package com.kolayvergi.dto.response;

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
public class BorcResponse {
    private UUID id;
    private BigDecimal toplamBorc;
    private BigDecimal kalanBorc;
}
