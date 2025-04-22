package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vergi_kasasi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VergiKasasi extends BaseEntity {

    private VergiTuru vergiTuru;

    private BigDecimal toplamBakiye;
}
