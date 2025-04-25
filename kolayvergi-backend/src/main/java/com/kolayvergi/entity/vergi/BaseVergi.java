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
public abstract class BaseVergi extends BaseEntity {

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal fiyat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false)
    private Alisveris alisveris;
}