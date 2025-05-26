package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AracBilgisiCreateRequest {

    @NotBlank(message = "Marka boş olamaz.")
    private String marka;

    @NotBlank(message = "Model boş olamaz.")
    private String model;

    @NotNull(message = "İlk tescil yılı boş olamaz.")
    @Enumerated(EnumType.STRING)
    private IlkTescilYili ilkTescilYili;

    @NotNull(message = "Motor silindir hacmi boş olamaz.")
    @Enumerated(EnumType.STRING)
    private MotorSilindirHacmi motorSilindirHacmi;

    @NotNull(message = "Araç tipi boş olamaz.")
    @Enumerated(EnumType.STRING)
    private AracTipi aracTipi;

    @NotNull(message = "Araç yaşı boş olamaz.")
    @Enumerated(EnumType.STRING)
    private AracYasi aracYasi;

    @NotNull(message = "Araç kapasitesi boş olamaz.")
    @Enumerated(EnumType.STRING)
    private AracKapasitesi aracKapasitesi;

    @NotNull(message = "Araç ağırlığı boş olamaz.")
    @Enumerated(EnumType.STRING)
    private AracAgirligi aracAgirligi;
}
