package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alisveris")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder -> Alisveris Mapper'da hataya sebep oldu. Sebebi notion ortakta...
public class Alisveris extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @Column(name = "urun_turu", nullable = false)
    @Enumerated(EnumType.STRING)
    private UrunTuru urunTuru;

    @Column(name = "tutar", nullable = false, precision = 12, scale = 2)//precision ve scale tutar'in virgulden once sonra kac basamak olacagini belirler.
    private BigDecimal tutar;

}

