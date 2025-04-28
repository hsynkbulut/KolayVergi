package com.kolayvergi.dto.response;

import lombok.Getter;

@Getter
public class AracBilgisiResponse {
    private String marka;
    private String model;
    private Integer ilkTescilYili;
    private Integer motorSilindirHacmi;
    private String aracTipi;
    private Integer aracYasi;
}
