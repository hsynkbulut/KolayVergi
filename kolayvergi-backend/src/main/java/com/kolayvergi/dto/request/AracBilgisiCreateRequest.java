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

    @NotBlank(message = "validation.marka_bos_olamaz")
    private String marka;

    @NotBlank(message = "validation.model_bos_olamaz")
    private String model;

    @NotNull(message = "validation.ilk_tescil_yili_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private IlkTescilYili ilkTescilYili;

    @NotNull(message = "validation.motor_silindir_hacmi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private MotorSilindirHacmi motorSilindirHacmi;

    @NotNull(message = "validation.arac_tipi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private AracTipi aracTipi;

    @NotNull(message = "validation.arac_yasi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private AracYasi aracYasi;

    @NotNull(message = "validation.arac_kapasitesi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private AracKapasitesi aracKapasitesi;

    @NotNull(message = "validation.arac_agirligi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    private AracAgirligi aracAgirligi;
}
