package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alisveris")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alisveris extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @Column(name = "urun_turu", nullable = false)
    @Enumerated(EnumType.STRING)
    private UrunTuru urunTuru;

    private BigDecimal tutar;

}

