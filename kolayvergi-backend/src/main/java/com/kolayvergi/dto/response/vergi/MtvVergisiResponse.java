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
public class MtvVergisiResponse {
    private Long alisverisId;
    private BigDecimal fiyat;
    private String aracTipi;
    private Integer aracYasi;
    private Integer motorSilindirHacmi;
    private Integer ilkTescilYili;
}
