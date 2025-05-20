package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "taksitler")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Taksit extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odeme_plani_id", nullable = false)
    private OdemePlani odemePlani;

    @Column(name = "taksit_no", nullable = false, unique = true)
    private String taksitNo;

    @Column(name = "taksit_tutari", nullable = false, precision = 12, scale = 2)
    private BigDecimal taksitTutari;

    @Column(name = "odeme_tarihi")
    private LocalDate odemeTarihi;

    @Column(name = "son_odeme_tarihi", nullable = false)
    private LocalDate sonOdemeTarihi;

    @Enumerated(EnumType.STRING)
    @Column(name = "durum", nullable = false)
    private OdemeDurumu durum;

    @Enumerated(EnumType.STRING)
    @Column(name = "odeme_turu", nullable = false)
    private OdemeTuru odemeTuru;
}
