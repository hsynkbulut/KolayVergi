package com.kolayvergi.dto.response.vergi;

import lombok.*;

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
