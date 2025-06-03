package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alisverisler")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alisveris extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @Column(name = "urun_turu", nullable = false)
    @Enumerated(EnumType.STRING)
    private UrunTuru urunTuru;

    @Column(name = "tutar", nullable = false, precision = 12, scale = 2)
    private BigDecimal tutar;

    @Column(name = "taksit_sayisi", nullable = false)
    private Integer taksitSayisi;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "arac_bilgisi_id")
    private AracBilgisi aracBilgisi;

    @OneToOne(mappedBy = "alisveris", cascade = CascadeType.ALL, optional = false)
    private OdemePlani odemePlani;

    @OneToOne(mappedBy = "alisveris", cascade = CascadeType.ALL, orphanRemoval = true)
    private KdvVergisi kdvVergisi;

    @OneToOne(mappedBy = "alisveris", cascade = CascadeType.ALL, orphanRemoval = true)
    private MtvVergisi mtvVergisi;

    @OneToOne(mappedBy = "alisveris", cascade = CascadeType.ALL, orphanRemoval = true)
    private OtvVergisi otvVergisi;
}