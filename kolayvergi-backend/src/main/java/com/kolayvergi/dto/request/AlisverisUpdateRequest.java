package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.UrunTuru;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlisverisUpdateRequest {
    @NotNull(message = "Kullanıcı ID alanı boş bırakılamaz")
    private Long kullaniciId;

    @NotNull(message = "Urun Turu alanı boş bırakılamaz")
    private UrunTuru urunTuru;

    @Valid
    private AracBilgisiCreateRequest aracBilgisi; // sadece OTOMOBIL için zorunlu

    @NotNull(message = "Tutar alanı boş bırakılamaz")
    private BigDecimal tutar;

    @NotNull(message = "Taksit sayısı boş bırakılamaz")
    private Integer taksitSayisi;
}
