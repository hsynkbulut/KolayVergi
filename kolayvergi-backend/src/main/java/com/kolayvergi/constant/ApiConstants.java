package com.kolayvergi.constant;

public class ApiConstants {
    public static final String API_BASE = "/api/v1";
    public static final String KULLANICILAR = API_BASE + "/kullanicilar";
    public static final String BORCLAR = API_BASE + "/borclar";
    public static final String ALISVERISLER = API_BASE + "/alisverisler";
    public static final String ODEMELER = API_BASE + "/odemeler";
    public static final String TAKSITLER = API_BASE + "/taksitler";
    public static final String AUTH = API_BASE + "/auth";
    public static final String ID = "/{id}";
    public static final String DELETE = "/{id}/delete";
    public static final String DELETE_ME = "/me/delete";
    public static final String UPDATE = "/update";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_REFRESH = "/refresh";
    public static final String AUTH_REGISTER = "/register";
    public static final String TAKSIT_ODEME = "/taksit-odeme";
    public static final String TAKSIT_ODEME_DETAY = "/{taksitNo}";


    private ApiConstants() {
        throw new UnsupportedOperationException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }
}