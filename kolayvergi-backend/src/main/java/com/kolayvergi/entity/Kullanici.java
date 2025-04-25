package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kullanicilar")
public class Kullanici extends BaseEntity {

    @Column(length = 10, unique = true, nullable = false)
    @NotBlank(message = "VKN boş olamaz.")
    @Pattern(regexp = "\\d{10}", message = "VKN tam olarak 10 haneli ve sadece rakam olmalıdır.")
    private String vkn;

    @Column(length = 11, unique = true, nullable = false)
    @NotBlank(message = "TCKN boş olamaz.")
    @Pattern(regexp = "\\d{11}", message = "TCKN tam olarak 11 haneli ve sadece rakam olmalıdır.")
    private String tckn;

    @NotBlank(message = "Ad boş olamaz.")
    private String ad;

    @NotBlank(message = "Soyad boş olamaz.")
    private String soyad;

    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    @NotBlank(message = "Email boş olamaz.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Şifre boş olamaz.")
    @Size(min = 4, max = 16, message = "Şifre 4-16 karakter arası olmalıdır.")
    private String sifre;

    @NotNull(message = "Yaş boş olamaz.")
    @Min(value = 18, message = "18 yaşından küçük kullanıcı kayıt olamaz.")
    @Max(value = 150, message = "Yaş 150’den büyük olamaz.")
    private Integer yas;

    @NotBlank(message = "Cinsiyet boş olamaz.")
    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    @NotBlank(message = "Meslek boş olamaz.")
    @Enumerated(EnumType.STRING)
    private Meslek meslek;

    @NotNull(message = "Maaş boş olamaz.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maaş sıfırdan büyük olmalıdır.")
    private BigDecimal maas;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<Alisveris> alisverisler;

    @OneToOne(mappedBy = "kullanici", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Borc borc;
}
