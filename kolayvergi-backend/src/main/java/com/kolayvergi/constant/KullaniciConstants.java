package com.kolayvergi.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KullaniciConstants {
    public static final String KULLANICI_BULUNAMADI = "Kullanıcı bulunamadı";
    public static final String KULLANICI_EMAIL_BULUNAMADI = "Kullanıcı bulunamadı: %s";
    public static final String OTURUMDA_KULLANICI_BULUNAMADI = "Oturum açmış kullanıcı bulunamadı";
    public static final String KULLANICI_ID_BULUNAMADI = "ID ile kullanıcı bulunamadı: %s";
    public static final String AD_BOS_OLAMAZ = "Ad alanı boş olamaz";
    public static final String SOYAD_BOS_OLAMAZ = "Soyad alanı boş olamaz";
    public static final String EMAIL_BOS_OLAMAZ = "Email alanı boş olamaz";
    public static final String GECERLI_EMAIL = "Geçerli bir email adresi giriniz";
    public static final String SIFRE_BOS_OLAMAZ = "Şifre alanı boş olamaz";
    public static final String YAS_BOS_OLAMAZ = "Yaş alanı boş olamaz";
    public static final String YAS_18_KUCUK_OLAMAZ = "18 yaşından küçük kullanıcı kayıt olamaz";
    public static final String YAS_150_BUYUK_OLAMAZ = "Yaş 150'den büyük olamaz";
    public static final String CINSIYET_BOS_OLAMAZ = "Cinsiyet alanı boş olamaz";
    public static final String MESLEK_BOS_OLAMAZ = "Meslek alanı boş olamaz";
    public static final String MAAS_BOS_OLAMAZ = "Maaş alanı boş olamaz";
    public static final String MAAS_SIFIRDAN_BUYUK_OLMALI = "Maaş sıfırdan büyük olmalıdır";
} 