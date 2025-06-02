package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.*;
import com.kolayvergi.constant.ValidationConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AracBilgisiCreateRequest {

    @NotBlank(message = ValidationConstants.MARKA_BOS_OLAMAZ)
    private String marka;

    @NotBlank(message = ValidationConstants.MODEL_BOS_OLAMAZ)
    private String model;

    @NotNull(message = ValidationConstants.ILK_TESCIL_YILI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private IlkTescilYili ilkTescilYili;

    @NotNull(message = ValidationConstants.MOTOR_SILINDIR_HACMI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private MotorSilindirHacmi motorSilindirHacmi;

    @NotNull(message = ValidationConstants.ARAC_TIPI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private AracTipi aracTipi;

    @NotNull(message = ValidationConstants.ARAC_YASI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private AracYasi aracYasi;

    @NotNull(message = ValidationConstants.ARAC_KAPASITESI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private AracKapasitesi aracKapasitesi;

    @NotNull(message = ValidationConstants.ARAC_AGIRLIGI_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private AracAgirligi aracAgirligi;
}
