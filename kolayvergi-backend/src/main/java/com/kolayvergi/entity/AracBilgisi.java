package com.kolayvergi.entity;

import com.kolayvergi.converter.*;
import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "arac_bilgileri")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisi extends BaseEntity {
    private String marka;
    private String model;

    @Convert(converter = IlkTescilYiliConverter.class)
    private IlkTescilYili ilkTescilYili;

    @Convert(converter = MotorSilindirHacmiConverter.class)
    private MotorSilindirHacmi motorSilindirHacmi;

    @Convert(converter = AracTipiConverter.class)
    private AracTipi aracTipi;

    @Convert(converter = AracYasiConverter.class)
    private AracYasi aracYasi;

    @Convert(converter = AracKapasitesiConverter.class)
    private AracKapasitesi aracKapasitesi;

    @Convert(converter = AracAgirligiConverter.class)
    private AracAgirligi aracAgirligi;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false, unique = true)
    private Alisveris alisveris;
}
