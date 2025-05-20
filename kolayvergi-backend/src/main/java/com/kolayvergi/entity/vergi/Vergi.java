package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public abstract class Vergi extends BaseEntity {

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal oran;//vergi orani

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal tutar;//vergi tutari

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal matrah; //vergisiz hali

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false, unique = true)
    private Alisveris alisveris;
}