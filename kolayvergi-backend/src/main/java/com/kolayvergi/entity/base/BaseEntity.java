package com.kolayvergi.entity.base;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "olusturulma_tarihi", nullable = false, updatable = false)
    private LocalDateTime olusturulmaTarihi;

    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;

    @Version
    private Long version;

    @PrePersist
    protected void onCreate() {
        olusturulmaTarihi = LocalDateTime.now();
        guncellemeTarihi = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        guncellemeTarihi = LocalDateTime.now();
    }

}
