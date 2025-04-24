package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vergi_kasasi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasi extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private VergiTuru vergiTuru;

    private BigDecimal toplamBakiye;
}
