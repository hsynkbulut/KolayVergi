package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.constant.ValidationConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "borclar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borc extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", unique = true)
    private Kullanici kullanici;

    @NotNull(message = ValidationConstants.TOPLAM_BORC_BOS_OLAMAZ)
    @Column(name = "toplam_borc")
    private BigDecimal toplamBorc;

    @Column(name = "kalan_borc")
    private BigDecimal kalanBorc;
}
