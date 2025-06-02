package com.kolayvergi.constant;

public final class AuthConstants {
    private AuthConstants() {
        throw new IllegalStateException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }

    public static final String GECERSIZ_REFRESH_TOKEN = "Geçersiz refresh token";
    public static final String EMAIL_KULLANIMDA = "Bu email adresi zaten kullanılıyor";
} 