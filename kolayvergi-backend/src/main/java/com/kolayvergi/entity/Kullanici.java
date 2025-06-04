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
import org.hibernate.annotations.ColumnTransformer;
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

    @NotBlank(message = "validation.ad_bos_olamaz")
    private String ad;

    @NotBlank(message = "validation.soyad_bos_olamaz")
    private String soyad;

    @NotBlank(message = "validation.email_bos_olamaz")
    @Email(message = "validation.gecerli_email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = ("validation.sifre_bos_olamaz"))
    @Column(nullable = false)
    private String sifre;

    @NotNull(message = "validation.yas_bos_olamaz")
    @Min(value = 18, message = "validation.yas_min")
    @Max(value = 150, message = "validation.yas_max")
    private Integer yas;

    @NotNull(message = "validation.cinsiyet_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "cinsiyet", columnDefinition = "cinsiyet_enum not null", nullable = false)
    @ColumnTransformer(write = "?::cinsiyet_enum")
    private Cinsiyet cinsiyet;

    @NotNull(message = "validation.meslek_bos_olamaz")
    @Enumerated(EnumType.STRING)
    @Column(name = "meslek", columnDefinition = "meslek_enum not null", nullable = false)
    @ColumnTransformer(write = "?::meslek_enum")
    private Meslek meslek;

    @NotNull(message = "validation.maas_bos_olamaz")
    @DecimalMin(value = "0.0", inclusive = false, message = "validation.maas_min")
    private BigDecimal maas;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    private List<Alisveris> alisverisler;

    @OneToOne(mappedBy = "kullanici", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Borc borc;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", columnDefinition = "role_enum not null", nullable = false)
    @ColumnTransformer(write = "?::role_enum")
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
