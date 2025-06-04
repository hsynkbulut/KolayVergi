package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import com.kolayvergi.validator.annotation.ValidTCKN;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class KullaniciCreateRequest {

    @ValidTCKN
    private String tckn;

    @NotBlank(message = "validation.ad_bos_olamaz")
    private String ad;

    @NotBlank(message = "validation.soyad_bos_olamaz")
    private String soyad;

    @Email(message = "validation.gecerli_email")
    @NotBlank(message = "validation.email_bos_olamaz")
    private String email;

    @NotBlank(message = ("validation.sifre_bos_olamaz"))
    @Size(min = 4, max = 16, message = "validation.sifre_uzunluk")
    private String sifre;

    @NotNull(message = "validation.yas_bos_olamaz")
    @Min(value = 18, message = "validation.yas_min")
    @Max(value = 150, message = "validation.yas_max")
    private Integer yas;

    @NotNull(message = "validation.cinsiyet_bos_olamaz")
    private Cinsiyet cinsiyet;

    @NotNull(message = "validation.meslek_bos_olamaz")
    private Meslek meslek;

    @NotNull(message = "validation.maas_bos_olamaz")
    @DecimalMin(value = "0.0", inclusive = false, message = "validation.maas_min")
    private BigDecimal maas;

}
