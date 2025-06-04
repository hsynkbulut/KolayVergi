package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

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

    @NotNull(message = "validation.urun_turu_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "urun_turu", columnDefinition = "urun_turu_enum not null", nullable = false)
    @ColumnTransformer(write = "?::urun_turu_enum")
    private UrunTuru urunTuru;

    @NotNull(message = "validation.tutar_bos_olamaz")
    @Column(name = "tutar", nullable = false, precision = 12, scale = 2)
    private BigDecimal tutar;

    @NotNull(message = "validation.taksit_sayisi_bos_olamaz")
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