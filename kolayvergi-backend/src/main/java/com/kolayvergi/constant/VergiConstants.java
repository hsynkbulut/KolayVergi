package com.kolayvergi.constant;

public final class VergiConstants {
    private VergiConstants() {
        throw new IllegalStateException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }

    public static final String MTV_ARAC_BILGISI_GEREKLI = "MTV hesaplaması için araç bilgisi gereklidir";
    public static final String KDV_ARAC_BILGISI_GEREKLI = "KDV hesaplaması için araç bilgisi gereklidir";
    public static final String KDV_ORANI_TANIMLI_DEGIL = "%s için KDV oranı tanımlı değil";
    public static final String OTV_ARAC_BILGISI_GEREKLI = "OTV hesaplaması için araç bilgisi gereklidir";
    public static final String OTV_ORANI_TANIMLI_DEGIL = "%s için OTV oranı tanımlı değil";
    public static final String VERGI_HESAPLANAMADI = "Vergi hesaplanamadı";
    public static final String VERGI_KAYDEDILEMEDI = "Vergi kaydedilemedi";
    public static final String VERGI_GUNCELLENEMEDI = "Vergi güncellenemedi";
    public static final String VERGI_SILINEMEDI = "Vergi silinemedi";
    public static final String VERGI_BULUNAMADI = "Vergi bulunamadı";
    public static final String KDV_VERGISI_BULUNAMADI = "KDV Vergisi bulunamadı: %s";
    public static final String MTV_VERGISI_BULUNAMADI = "MTV Vergisi bulunamadı: %s";
    public static final String OTV_VERGISI_BULUNAMADI = "OTV Vergisi bulunamadı: %s";
    public static final String VERGI_TUTARI_SIFIR = "Vergi tutarı sıfır olamaz";
    public static final String VERGI_TUTARI_NEGATIF = "Vergi tutarı negatif olamaz";
    public static final String VERGI_TARIHI_GECERSIZ = "Vergi tarihi geçersiz";
    public static final String VERGI_DURUMU_GECERSIZ = "Vergi durumu geçersiz";
    public static final String VERGI_TURU_GECERSIZ = "Vergi türü geçersiz";
} 