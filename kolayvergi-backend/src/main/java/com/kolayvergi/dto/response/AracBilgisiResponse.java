package com.kolayvergi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisiResponse {
    private String marka;
    private String model;
    private Integer ilkTescilYili;
    private Integer motorSilindirHacmi;
    private String aracTipi;
    private Integer aracYasi;
}
