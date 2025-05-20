package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kdv_vergisi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KdvVergisi extends Vergi {
    @Enumerated(EnumType.STRING)
    private UrunTuru urunTuru;
}

