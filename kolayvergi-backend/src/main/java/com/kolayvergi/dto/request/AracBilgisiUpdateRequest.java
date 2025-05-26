package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AracBilgisiUpdateRequest {
    @NotNull(message = "ID bilgisi boş olamaz.")
    private UUID id;

    private String marka;

    private String model;

    @Enumerated(EnumType.STRING)
    private IlkTescilYili ilkTescilYili;

    @Enumerated(EnumType.STRING)
    private MotorSilindirHacmi motorSilindirHacmi;

    @Enumerated(EnumType.STRING)
    private AracTipi aracTipi;

    @Enumerated(EnumType.STRING)
    private AracYasi aracYasi;

    @Enumerated(EnumType.STRING)
    private AracKapasitesi aracKapasitesi;

    @Enumerated(EnumType.STRING)
    private AracAgirligi aracAgirligi;
}
