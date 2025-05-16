package com.kolayvergi.entity.vergi;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.persistence.*;
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

