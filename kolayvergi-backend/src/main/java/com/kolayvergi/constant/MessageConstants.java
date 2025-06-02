package com.kolayvergi.constant;

public final class MessageConstants {
    private MessageConstants() {
        throw new IllegalStateException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }

    public static final String GONDERILEN_VERILERDE_HATA = "Gönderdiğiniz verilerde eksiklik veya hata var.";
    public static final String VALIDATION_HATASI = "Validation hatası oluştu.";
    public static final String KAYIT_BULUNAMADI = "İstenen kayıt bulunamadı.";
    public static final String URL_PARAMETRE_EKSIK = "URL parametrelerinden biri eksik.";
    public static final String GECERSIZ_VERI = "Geçersiz veri gönderildi.";
    public static final String SISTEM_HATASI = "Sistemde bir hata oluştu.";
    public static final String BEKLENMEYEN_HATA = "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyin.";
} 