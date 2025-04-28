package com.kolayvergi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AracBilgisiUpdateRequest {
    @NotNull(message = "ID bilgisi boş olamaz.")
    private Long id;

    private String marka;

    private String model;

    private Integer ilkTescilYili;

    private Integer motorSilindirHacmi;

    private String aracTipi;

    private Integer aracYasi;
}
