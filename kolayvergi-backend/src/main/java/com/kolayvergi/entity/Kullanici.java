package com.kolayvergi.entity;

import com.kolayvergi.entity.base.BaseEntity;
import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import com.kolayvergi.entity.enums.Role;
import com.kolayvergi.validator.annotation.ValidTCKN;
import com.kolayvergi.validator.annotation.ValidVKN;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kullanicilar")
public class Kullanici extends BaseEntity implements UserDetails {

    @Column(length = 10, unique = true, nullable = false)
    @ValidVKN
    private String vkn;

    @Column(length = 11, unique = true, nullable = false)
    @ValidTCKN
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
    @Column(nullable = false)
    private String sifre;

    @NotNull(message = "Yaş boş olamaz.")
    @Min(value = 18, message = "18 yaşından küçük kullanıcı kayıt olamaz.")
    @Max(value = 150, message = "Yaş 150'den büyük olamaz.")
    private Integer yas;

    @NotNull(message = "Cinsiyet boş olamaz.")
    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    @NotNull(message = "Meslek boş olamaz.")
    @Enumerated(EnumType.STRING)
    private Meslek meslek;

    @NotNull(message = "Maaş boş olamaz.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maaş sıfırdan büyük olmalıdır.")
    private BigDecimal maas;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<Alisveris> alisverisler;

    @OneToOne(mappedBy = "kullanici", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Borc borc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(rol);
    }

    @Override
    public String getPassword() {
        return sifre;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
