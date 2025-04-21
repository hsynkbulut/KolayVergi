package com.kolayvergi.entity.base;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "olusturulma_tarihi", nullable = false, updatable = false)
    private LocalDateTime olusturulmaTarihi;

    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;

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
