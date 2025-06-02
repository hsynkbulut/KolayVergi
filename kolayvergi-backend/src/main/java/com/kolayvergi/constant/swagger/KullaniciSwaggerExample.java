package com.kolayvergi.constant.swagger;

public final class KullaniciSwaggerExample {
    
    private KullaniciSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    public static final String KULLANICI_RESPONSE = """
            {
                "ad": "Ahmet",
                "soyad": "Yılmaz",
                "email": "ahmet.yilmaz@example.com",
                "cinsiyet": "ERKEK",
                "meslek": "MUHENDIS",
                "yas": 30,
                "maas": 25000.00
            }
            """;

    public static final String KULLANICI_LISTESI_RESPONSE = """
            [
                {
                    "ad": "Ahmet",
                    "soyad": "Yılmaz",
                    "email": "ahmet.yilmaz@example.com",
                    "cinsiyet": "ERKEK",
                    "meslek": "MUHENDIS",
                    "yas": 30,
                    "maas": 25000.00
                },
                {
                    "ad": "Ayşe",
                    "soyad": "Demir",
                    "email": "ayse.demir@example.com",
                    "cinsiyet": "KADIN",
                    "meslek": "DOKTOR",
                    "yas": 35,
                    "maas": 35000.00
                }
            ]
            """;
} 