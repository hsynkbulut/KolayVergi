package com.kolayvergi.constant;

public class ApiConstants {
    public static final String API_BASE = "/api/v1";
    public static final String KULLANICILAR = API_BASE + "/kullanicilar";
    public static final String BORCLAR = API_BASE + "/borclar";
    public static final String VERGIKASASI = API_BASE + "/vergikasasi";
    public static final String ID = "/{id}";

    private ApiConstants() {
        throw new UnsupportedOperationException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }
}