package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
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
    private Integer ilkTescilYili;
    private Integer motorSilindirHacmi;
    private String aracTipi;
    private Integer aracYasi;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false, unique = true)
    private Alisveris alisveris;
}
