package com.kolayvergi.constant;

public final class OdemeConstants {
    private OdemeConstants() {
        throw new IllegalStateException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }

    public static final String TUTAR_FARKLI = "Girilen tutar beklenen tutardan farkli!";
    public static final String TAKSIT_BULUNAMADI = "Taksit bulunamadı: %s";
    public static final String ODEME_TURU_GECERSIZ = "Geçersiz ödeme türü: %s";
} 