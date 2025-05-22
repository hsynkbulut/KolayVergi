package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.*;
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

    private IlkTescilYili ilkTescilYili;

    private MotorSilindirHacmi motorSilindirHacmi;

    private AracTipi aracTipi;

    private AracYasi aracYasi;

    private AracKapasitesi aracKapasitesi;

    private AracAgirligi aracAgirligi;
}
