package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;


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
    @Column(name = "ilk_tescil_yili", columnDefinition = "ilk_tescil_yili_enum not null", nullable = false)
    @ColumnTransformer(write = "?::ilk_tescil_yili_enum")
    private IlkTescilYili ilkTescilYili;

    @NotNull(message = "validation.motor_silindir_hacmi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "motor_silindir_hacmi", columnDefinition = "motor_silindir_hacmi_enum not null", nullable = false)
    @ColumnTransformer(write = "?::motor_silindir_hacmi_enum")
    private MotorSilindirHacmi motorSilindirHacmi;

    @NotNull(message = "validation.arac_tipi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "arac_tipi", columnDefinition = "arac_tipi_enum not null", nullable = false)
    @ColumnTransformer(write = "?::arac_tipi_enum")
    private AracTipi aracTipi;

    @NotNull(message = "validation.arac_yasi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "arac_yasi", columnDefinition = "arac_yasi_enum not null", nullable = false)
    @ColumnTransformer(write = "?::arac_yasi_enum")
    private AracYasi aracYasi;

    @NotNull(message = "validation.arac_kapasitesi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "arac_kapasitesi", columnDefinition = "arac_kapasitesi_enum not null", nullable = false)
    @ColumnTransformer(write = "?::arac_kapasitesi_enum")
    private AracKapasitesi aracKapasitesi;

    @NotNull(message = "validation.arac_agirligi_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "arac_agirligi", columnDefinition = "arac_agirligi_enum not null", nullable = false)
    @ColumnTransformer(write = "?::arac_agirligi_enum")
    private AracAgirligi aracAgirligi;
}
