package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "otv_vergisi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtvVergisi extends Vergi {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arac_bilgisi_id")
    private AracBilgisi aracBilgisi;

    @Column(nullable = false)
    private UrunTuru urunTuru;

    @Column(nullable = false)
    private BigDecimal luksUrunKatSayisi;

}

