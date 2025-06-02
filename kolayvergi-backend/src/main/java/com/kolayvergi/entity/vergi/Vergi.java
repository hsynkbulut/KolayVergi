package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public abstract class Vergi extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id")
    private Alisveris alisveris;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arac_bilgisi_id")
    private AracBilgisi aracBilgisi;

    @Column(name = "matrah", nullable = false, precision = 12, scale = 2)
    private BigDecimal matrah;

    @Column(name = "oran", nullable = false, precision = 12, scale = 2)
    private BigDecimal oran;

    @Column(name = "tutar", nullable = false, precision = 12, scale = 2)
    private BigDecimal tutar;
}