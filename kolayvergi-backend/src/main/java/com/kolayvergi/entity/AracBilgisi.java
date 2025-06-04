package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Table(name = "arac_bilgileri")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisi extends BaseEntity {

    @NotBlank(message = "validation.marka_bos_olamaz")
    @Column(nullable = false)
    private String marka;

    @NotBlank(message = "validation.model_bos_olamaz")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "validation.ilk_tescil_yili_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IlkTescilYili ilkTescilYili;

    @NotNull(message = "validation.motor_silindir_hacmi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotorSilindirHacmi motorSilindirHacmi;

    @NotNull(message = "validation.arac_tipi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracTipi aracTipi;

    @NotNull(message = "validation.arac_yasi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracYasi aracYasi;

    @NotNull(message = "validation.arac_kapasitesi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracKapasitesi aracKapasitesi;

    @NotNull(message = "validation.arac_agirligi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracAgirligi aracAgirligi;
}
