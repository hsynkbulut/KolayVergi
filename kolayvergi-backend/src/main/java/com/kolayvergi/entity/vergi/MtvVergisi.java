package com.kolayvergi.entity.vergi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mtv_vergisi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MtvVergisi extends BaseVergi {

    @Column(nullable = false)
    private String aracTipi;

    @Column(nullable = false)
    private Integer aracYasi;

    @Column(nullable = false)
    private Integer motorSilindirHacmi;

    @Column(nullable = false)
    private Integer ilkTescilYili;
}
