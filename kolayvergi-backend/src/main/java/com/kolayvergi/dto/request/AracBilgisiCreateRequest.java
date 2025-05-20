package com.kolayvergi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AracBilgisiCreateRequest {

    @NotBlank(message = "Marka boş olamaz.")
    private String marka;

    @NotBlank(message = "Model boş olamaz.")
    private String model;

    @NotNull(message = "İlk tescil yılı boş olamaz.")
    @Min(value = 1900, message = "İlk tescil yılı geçersiz.")
    private Integer ilkTescilYili;

    @NotNull(message = "Motor silindir hacmi boş olamaz.")
    @Min(value = 50, message = "Motor hacmi çok düşük.")
    private Integer motorSilindirHacmi;

    @NotBlank(message = "Araç tipi boş olamaz.")
    private String aracTipi;

    @NotNull(message = "Araç yaşı boş olamaz.")
    @Min(value = 0, message = "Araç yaşı negatif olamaz.")
    private Integer aracYasi;
}
