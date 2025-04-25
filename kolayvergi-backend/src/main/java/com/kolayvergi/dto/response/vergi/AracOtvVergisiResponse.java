package com.kolayvergi.dto.response.vergi;

import lombok.*;

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
