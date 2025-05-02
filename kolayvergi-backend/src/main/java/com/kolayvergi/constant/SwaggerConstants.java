package com.kolayvergi.constant;

public class SwaggerConstants {

    // Kullanıcı
    public static final String CREATE_KULLANICI_SUMMARY = "Yeni kullanıcı oluştur";
    public static final String CREATE_KULLANICI_DESC = "Sisteme yeni bir kullanıcı ekler.";
    public static final String GET_KULLANICI_SUMMARY = "Kullanıcıyı ID'ye göre getir";
    public static final String GET_KULLANICI_DESC = "Verilen ID'ye sahip kullanıcıyı döner.";
    public static final String UPDATE_KULLANICI_SUMMARY = "Kullanıcı bilgilerini güncelle";
    public static final String UPDATE_KULLANICI_DESC = "Mevcut kullanıcı bilgilerini günceller.";
    public static final String DELETE_KULLANICI_SUMMARY = "Kullanıcıyı sil";
    public static final String DELETE_KULLANICI_DESC = "Belirtilen ID'ye sahip kullanıcıyı sistemden siler.";

    // Alışveriş
    public static final String CREATE_ALISVERIS_SUMMARY = "Yeni alışveriş oluştur";
    public static final String CREATE_ALISVERIS_DESC = "Sisteme yeni bir alışveriş kaydı ekler ve bağlı vergi, ödeme planı, taksitleri otomatik oluşturur.";
    public static final String GET_ALISVERIS_SUMMARY = "Alışverişi ID'ye göre getir";
    public static final String GET_ALISVERIS_DESC = "Verilen ID'ye sahip alışveriş kaydını getirir.";
    public static final String UPDATE_ALISVERIS_SUMMARY = "Alışveriş kaydını güncelle";
    public static final String UPDATE_ALISVERIS_DESC = "Alışveriş verisini ve ilişkili vergi, ödeme, taksit verilerini günceller.";
    public static final String DELETE_ALISVERIS_SUMMARY = "Alışveriş kaydını sil";
    public static final String DELETE_ALISVERIS_DESC = "Verilen ID'ye sahip alışverişi ve ilişkili kayıtları siler.";

    // Borç
    public static final String GET_BORC_SUMMARY = "Borç bilgisini ID ile getir";
    public static final String GET_BORC_DESC = "Verilen ID'ye sahip borç kaydını döner.";

    // Ödeme
    public static final String TAKSIT_ODE_SUMMARY = "Taksit öde";
    public static final String TAKSIT_ODE_DESC = "Verilen bilgilere göre bir taksit ödemesi gerçekleştirir.";
    public static final String TAKSIT_ODEME_BILGISI_SUMMARY = "Taksit ödeme bilgisi getir";
    public static final String TAKSIT_ODEME_BILGISI_DESC = "Taksit numarası ve ödeme türüne göre ödeme bilgisini döner.";

    private SwaggerConstants() {
        throw new UnsupportedOperationException("Bu bir yardımcı sınıftır ve nesnesi oluşturulamaz!");
    }
}
