package com.kolayvergi.constant.swagger;

public final class AuthSwaggerExample {
    
    private AuthSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    public static final String JWT_RESPONSE_ORNEK = """
            {
                "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                "email": "ahmet.yilmaz@example.com",
                "roles": ["ROLE_USER"]
            }
            """;

    public static final String LOGIN_REQUEST_ORNEK = """
            {
                "email": "ahmet.yilmaz@example.com",
                "password": "Gizli123!"
            }
            """;

    public static final String REGISTER_REQUEST = """
            {
                "tckn": "10000000146",
                "ad": "Ahmet",
                "soyad": "Yılmaz",
                "email": "ahmet.yilmaz@example.com",
                "sifre": "Gizli123!",
                "yas": 30,
                "cinsiyet": "ERKEK",
                "meslek": "MUHENDIS",
                "maas": 25000.00
            }
            """;

    public static final String UPDATE_PROFILE_REQUEST = """
            {
                "vkn": "1234567890",
                "tckn": "10000000146",
                "ad": "Hamdi",
                "soyad": "Yılmaz",
                "email": "hamdi.yilmaz@example.com",
                "yas": 35,
                "cinsiyet": "ERKEK",
                "meslek": "MEMUR",
                "maas": 25000.00
            }
            """;
} 