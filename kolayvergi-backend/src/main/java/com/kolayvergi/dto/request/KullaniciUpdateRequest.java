package com.kolayvergi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class KullaniciUpdateRequest {

    @NotBlank(message = "Ad boş olamaz.")
    private String ad;

    @NotBlank(message = "Soyad boş olamaz.")
    private String soyad;

    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    @NotBlank(message = "Email boş olamaz.")
    private String email;

    @NotBlank(message = "Şifre boş olamaz.")
    @Size(min = 4, max = 16, message = "Şifre 4-16 karakter arası olmalıdır.")
    private String sifre;

    @NotNull(message = "Yaş boş olamaz.")
    @Min(value = 18, message = "18 yaşından küçük kullanıcı kayıt olamaz.")
    @Max(value = 150, message = "Yaş 150’den büyük olamaz.")
    private Integer yas;

    @NotBlank(message = "Cinsiyet boş olamaz.")
    private String cinsiyet;

    @NotBlank(message = "Meslek boş olamaz.")
    private String meslek;

    @NotNull(message = "Maaş boş olamaz.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maaş sıfırdan büyük olmalıdır.")
    private BigDecimal maas;

}
