package com.kolayvergi.dto.response.vergi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracOtvVergisiResponse {
    private Long alisverisId;
    private BigDecimal fiyat;
    private String aracTipi;
    private Integer motorSilindirHacmi;
}
