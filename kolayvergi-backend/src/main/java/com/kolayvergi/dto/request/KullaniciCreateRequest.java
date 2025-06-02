package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import com.kolayvergi.constant.ValidationConstants;
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

    @NotBlank(message = ValidationConstants.AD_BOS_OLAMAZ)
    private String ad;

    @NotBlank(message = ValidationConstants.SOYAD_BOS_OLAMAZ)
    private String soyad;

    @Email(message = ValidationConstants.GECERLI_EMAIL)
    @NotBlank(message = ValidationConstants.EMAIL_BOS_OLAMAZ)
    private String email;

    @NotBlank(message = ValidationConstants.SIFRE_BOS_OLAMAZ)
    @Size(min = 4, max = 16, message = ValidationConstants.SIFRE_UZUNLUK)
    private String sifre;

    @NotNull(message = ValidationConstants.YAS_BOS_OLAMAZ)
    @Min(value = 18, message = ValidationConstants.YAS_MIN)
    @Max(value = 150, message = ValidationConstants.YAS_MAX)
    private Integer yas;

    @NotNull(message = ValidationConstants.CINSIYET_BOS_OLAMAZ)
    private Cinsiyet cinsiyet;

    @NotNull(message = ValidationConstants.MESLEK_BOS_OLAMAZ)
    private Meslek meslek;

    @NotNull(message = ValidationConstants.MAAS_BOS_OLAMAZ)
    @DecimalMin(value = "0.0", inclusive = false, message = ValidationConstants.MAAS_MIN)
    private BigDecimal maas;

}
