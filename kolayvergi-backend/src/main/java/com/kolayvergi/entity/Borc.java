package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "borclar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borc extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = true, insertable=false, updatable=false)
    private Kullanici kullanici;

    @Column(name = "kullanici_id", nullable = false)
    private Long kullaniciId;

    @NotNull(message = "Toplam borç alanı boş olamaz!")
    @Column(name = "toplam_borc", nullable = false)
    private BigDecimal toplamBorc;

    @Column(name = "kalan_borc")
    private BigDecimal kalanBorc;
}
