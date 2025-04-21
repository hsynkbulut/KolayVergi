package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kullanicilar")
public class Kullanici extends BaseEntity {

    private Long vkn;

    private Long tckn;

    private String ad;

    private String soyad;

    private String email;

    private String sifre;

    private Integer yas;

    private String cinsiyet;

    private String meslek;

    private BigDecimal maas;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<Alisveris> alisverisler;

}
