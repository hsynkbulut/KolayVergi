package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.math.BigDecimal;

@Entity
@Table(name = "otv_vergisi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtvVergisi extends Vergi {

    @Enumerated(EnumType.STRING)
    @Column(name = "urun_turu", columnDefinition = "urun_turu_enum not null", nullable = false)
    @ColumnTransformer(write = "?::urun_turu_enum")
    private UrunTuru urunTuru;

    @Column(nullable = false)
    private BigDecimal luksUrunKatSayisi;

}

