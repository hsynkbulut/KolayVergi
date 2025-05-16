package com.kolayvergi.entity.vergi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "arac_otv_vergisi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracOtvVergisi extends Vergi {

    @Column(nullable = false)
    private String aracTipi;

    @Column(nullable = false)
    private Integer motorSilindirHacmi;
}

