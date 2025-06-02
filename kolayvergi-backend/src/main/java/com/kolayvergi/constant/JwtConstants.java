package com.kolayvergi.constant;

public final class JwtConstants {
    private JwtConstants() {
        throw new IllegalStateException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT_TOKEN_GECERSIZ = "Geçersiz JWT token";
    public static final String JWT_TOKEN_SURESI_DOLMUS = "JWT token süresi dolmuş";
    public static final String JWT_TOKEN_IMZASI_GECERSIZ = "JWT token imzası geçersiz";
    public static final String JWT_TOKEN_DOGRULAMA_YAPILAMADI = "JWT token doğrulaması yapılamadı";
    public static final String JWT_TOKEN_DESTEKLENMIYOR = "Desteklenmeyen JWT token";
    public static final String JWT_CLAIMS_STRING_BOS = "JWT claims string boş";
} 