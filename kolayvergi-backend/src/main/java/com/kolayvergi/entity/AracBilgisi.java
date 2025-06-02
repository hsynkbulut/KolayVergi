package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "arac_bilgileri")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisi extends BaseEntity {

    @Column(nullable = false)
    private String marka;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IlkTescilYili ilkTescilYili;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotorSilindirHacmi motorSilindirHacmi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracTipi aracTipi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracYasi aracYasi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracKapasitesi aracKapasitesi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AracAgirligi aracAgirligi;
}
