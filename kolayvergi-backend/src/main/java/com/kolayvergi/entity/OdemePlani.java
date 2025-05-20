package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "odeme_planlari")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdemePlani extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alisveris_id", nullable = false, unique = true)
    private Alisveris alisveris;

    @Column(name = "toplam_odenecek_tutar", nullable = false, precision = 12, scale = 2)
    private BigDecimal toplamOdenecekTutar;

    @Column(name = "toplam_odenmis_tutar", nullable = false, precision = 12, scale = 2)
    private BigDecimal toplamOdenmisTutar;

    @Column(name = "toplam_taksit_sayisi", nullable = false)
    private int toplamTaksitSayisi;

    @Column(name = "kalan_taksit_sayisi", nullable = false)
    private int kalanTaksitSayisi;

    @OneToMany(mappedBy = "odemePlani", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Taksit> taksitler;
}
