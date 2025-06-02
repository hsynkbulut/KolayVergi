package com.kolayvergi.entity;

import com.kolayvergi.constant.KullaniciConstants;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

    @NotBlank(message = KullaniciConstants.AD_BOS_OLAMAZ)
    private String ad;

    @NotBlank(message = KullaniciConstants.SOYAD_BOS_OLAMAZ)
    private String soyad;

    @NotBlank(message = KullaniciConstants.EMAIL_BOS_OLAMAZ)
    @Email(message = KullaniciConstants.GECERLI_EMAIL)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = KullaniciConstants.SIFRE_BOS_OLAMAZ)
    @Column(nullable = false)
    private String sifre;

    @NotNull(message = KullaniciConstants.YAS_BOS_OLAMAZ)
    @Min(value = 18, message = KullaniciConstants.YAS_18_KUCUK_OLAMAZ)
    @Max(value = 150, message = KullaniciConstants.YAS_150_BUYUK_OLAMAZ)
    private Integer yas;

    @NotNull(message = KullaniciConstants.CINSIYET_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    @NotNull(message = KullaniciConstants.MESLEK_BOS_OLAMAZ)
    @Enumerated(EnumType.STRING)
    private Meslek meslek;

    @NotNull(message = KullaniciConstants.MAAS_BOS_OLAMAZ)
    @DecimalMin(value = "0.0", inclusive = false, message = KullaniciConstants.MAAS_SIFIRDAN_BUYUK_OLMALI)
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
