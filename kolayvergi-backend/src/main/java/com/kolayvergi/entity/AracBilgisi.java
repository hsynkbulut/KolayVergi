package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisi extends BaseEntity {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false, unique = true)
    private Alisveris alisveris;
}
