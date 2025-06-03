package com.kolayvergi.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationConstants {
    // VKN Validasyon mesajları
    public static final String VKN_BOS_OLAMAZ = "VKN boş bırakılamaz.";
    public static final String VKN_10_HANELI_OLMALI = "VKN 10 haneli olmalıdır.";
    public static final String VKN_SADECE_RAKAM = "VKN sadece rakamlardan oluşmalıdır.";
    public static final String VKN_SIFIR_ILE_BASLAYAMAZ = "VKN 0 ile başlayamaz.";

    // TCKN Validasyon mesajları
    public static final String TCKN_BOS_OLAMAZ = "TCKN boş bırakılamaz.";
    public static final String TCKN_11_HANELI_OLMALI = "TCKN 11 haneli olmalıdır.";
    public static final String TCKN_SADECE_RAKAM = "TCKN sadece rakamlardan oluşmalıdır.";
    public static final String TCKN_SIFIR_ILE_BASLAYAMAZ = "TCKN 0 ile başlayamaz.";
    public static final String TCKN_GECERSIZ_FORMAT = "Geçersiz TCKN formatı.";

    // Genel Validasyon mesajları
    public static final String ID_BOS_OLAMAZ = "ID bilgisi boş olamaz.";
    public static final String TOPLAM_BORC_BOS_OLAMAZ = "Toplam borç alanı boş olamaz!";
    public static final String KALAN_BORC_BOS_OLAMAZ = "Kalan borç alanı boş olamaz!";
    public static final String MARKA_BOS_OLAMAZ = "Marka boş olamaz.";
    public static final String MODEL_BOS_OLAMAZ = "Model boş olamaz.";
    public static final String ILK_TESCIL_YILI_BOS_OLAMAZ = "İlk tescil yılı boş olamaz.";
    public static final String MOTOR_SILINDIR_HACMI_BOS_OLAMAZ = "Motor silindir hacmi boş olamaz.";
    public static final String ARAC_TIPI_BOS_OLAMAZ = "Araç tipi boş olamaz.";
    public static final String ARAC_YASI_BOS_OLAMAZ = "Araç yaşı boş olamaz.";
    public static final String ARAC_KAPASITESI_BOS_OLAMAZ = "Araç kapasitesi boş olamaz.";
    public static final String ARAC_AGIRLIGI_BOS_OLAMAZ = "Araç ağırlığı boş olamaz.";
    public static final String AD_BOS_OLAMAZ = "Ad boş olamaz.";
    public static final String SOYAD_BOS_OLAMAZ = "Soyad boş olamaz.";
    public static final String EMAIL_BOS_OLAMAZ = "Email boş olamaz.";
    public static final String GECERLI_EMAIL = "Geçerli bir e-posta adresi giriniz.";
    public static final String SIFRE_BOS_OLAMAZ = "Şifre boş olamaz.";
    public static final String SIFRE_UZUNLUK = "Şifre 4-16 karakter arası olmalıdır.";
    public static final String YAS_BOS_OLAMAZ = "Yaş boş olamaz.";
    public static final String YAS_MIN = "18 yaşından küçük kullanıcı kayıt olamaz.";
    public static final String YAS_MAX = "Yaş 150'den büyük olamaz.";
    public static final String CINSIYET_BOS_OLAMAZ = "Cinsiyet boş olamaz.";
    public static final String MESLEK_BOS_OLAMAZ = "Meslek boş olamaz.";
    public static final String MAAS_BOS_OLAMAZ = "Maaş boş olamaz.";
    public static final String MAAS_MIN = "Maaş sıfırdan büyük olmalıdır.";
    public static final String URUN_TURU_BOS_OLAMAZ = "Urun Turu alanı boş bırakılamaz";
    public static final String TUTAR_BOS_OLAMAZ = "Tutar alanı boş bırakılamaz";
    public static final String TAKSIT_SAYISI_BOS_OLAMAZ = "Taksit sayısı boş bırakılamaz";
} 