package com.kolayvergi.constant;

public class ApiConstants {
    public static final String API_BASE = "/api/v1";
    public static final String KULLANICILAR = API_BASE + "/kullanicilar";
    public static final String BORCLAR = API_BASE + "/borclar";
    public static final String VERGIKASASI = API_BASE + "/vergikasasi";
    public static final String ALISVERISLER = API_BASE + "/alisverisler";
    public static final String ARAC_BILGISI = API_BASE + "/arac-bilgisi";
    public static final String ID = "/{id}";
    public static final String ALISVERIS_ID = ALISVERISLER + "/{alisverisId}";
    public static final String ODEMELER = API_BASE + "/odemeler";

    private ApiConstants() {
        throw new UnsupportedOperationException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }
}